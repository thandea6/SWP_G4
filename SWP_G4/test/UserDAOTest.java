import dal.UserDAO;
import model.User;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;


public class UserDAOTest {
    private UserDAO userDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        // Mock các đối tượng JDBC
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Khởi tạo UserDAO với mockConnection
        userDAO = new UserDAO();
        userDAO.connection = mockConnection; // Giả lập kết nối CSDL
    }

    @Test
    void testGetUserByAccountId_Found() throws Exception {
        int accountId = 1;

        // Giả lập hành vi của Connection và PreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Giả lập dữ liệu trả về từ ResultSet phù hợp với model.User
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);
        when(mockResultSet.getNString(2)).thenReturn("Nguyễn Văn A");
        when(mockResultSet.getNString(3)).thenReturn("Hà Nội"); // Address
        when(mockResultSet.getString(4)).thenReturn("image_url.png");
        when(mockResultSet.getDouble(5)).thenReturn(50000.0);
        when(mockResultSet.getDate(6)).thenReturn(Date.valueOf("1990-01-01"));
        when(mockResultSet.getBoolean(7)).thenReturn(true);
        when(mockResultSet.getInt(8)).thenReturn(2);

        // Gọi phương thức cần test
        User user = userDAO.getUserByAccountId(accountId);

        // Kiểm tra kết quả
        assertNotNull(user);
        assertEquals(1, user.getUserId());
        assertEquals("Nguyễn Văn A", user.getFullName());
        assertEquals("Hà Nội", user.getAddress());
        assertEquals("image_url.png", user.getImage());
        assertEquals(50000.0, user.getAccountBalance());
        assertEquals(Date.valueOf("1990-01-01"), user.getDob());
        assertTrue(user.isGender());
        assertEquals(2, user.getAccountId());
    }

    @Test
    void testGetUserByAccountId_NotFound() throws Exception {
        int accountId = 99; // Giả lập accountId không tồn tại

        // Giả lập PreparedStatement và ResultSet trả về rỗng
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Gọi phương thức cần test
        User user = userDAO.getUserByAccountId(accountId);

        // Kiểm tra kết quả
        assertNull(user);
    }
}
