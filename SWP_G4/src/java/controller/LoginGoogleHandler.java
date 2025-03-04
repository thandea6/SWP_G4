package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.AccountDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;

/**
 * @author heaty566
 */
@WebServlet(urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        AccountDAO accdao = new AccountDAO();
        UserDAO userDao = new UserDAO();

        try {
            String accessToken = getToken(code);
            UserGoogleDto user = getUserInfo(accessToken);
            System.out.println(user);

            // Check if the account already exists
            Account acc = accdao.isExistAccount(user.getEmail(), user.getEmail(), "");
            System.out.println(acc);

            if (acc == null) {
                // Account doesn't exist, create a new one
                Account newAccount = new Account(0, user.getEmail(), "", 2);
                newAccount.setEmail(user.getEmail());

                // Add new account to the database
                int result = accdao.addAccount(newAccount);

                if (result > 0) {
                    // Set the ID of the newly created account
                    newAccount.setAccountId(result);

                    // Add new user associated with the account
                    User newUser = new User();
                    newUser.setAccountId(result);
                    newUser.setFullName(user.getName());
                    newUser.setImage(user.getPicture());

                    int isAdd = userDao.addUser(newUser);

                    if (isAdd > 0) {
                        // Successfully added user, create session and redirect
                        createSessionAndRedirect(request, response, newAccount);
                    } else {
                        // Handle failure to add user
                        throw new Exception("Failed to add user information.");
                    }
                } else {
                    // Handle failure to add account
                    throw new Exception("Failed to add account.");
                }
            } else {

                createSessionAndRedirect(request, response, acc);
            }
        } catch (Exception e) {
            handleException(request, response, e);
        }

    }

    private void createSessionAndRedirect(HttpServletRequest request, HttpServletResponse response, Account account)
            throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", account);
        response.sendRedirect("home");
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        e.printStackTrace(); // Log the exception (replace with a logger in real applications)
        request.setAttribute("error_message", "Failed to authenticate with Google. Please try again.");
        request.getRequestDispatcher("SignUpIn.jsp").forward(request, response);
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the +
    // sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
    }
}
