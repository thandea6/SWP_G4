USE [master]
GO
/****** Object:  Database [SWP_G4]    Script Date: 10/07/2024 5:20:53 CH ******/
CREATE DATABASE [SWP_G4]
GO
USE [SWP_G4]
GO
/****** Object:  Table [dbo].[account]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[accountId] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[created_at] [date] NULL,
	[updated_at] [date] NULL,
	[is_deleted] [bit] NULL,
	[roleId] [int] NULL,
	[phone] [varchar](20) NULL,
	[email] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brand]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand](
	[brandId] [int] IDENTITY(1,1) NOT NULL,
	[brandName] [varchar](255) NOT NULL,
	[image] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[brandId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[categoryId] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](255) NULL,
	[image] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[chatBox]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chatBox](
	[chatId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[shopId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[chatId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[color]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color](
	[colorId] [int] IDENTITY(1,1) NOT NULL,
	[colorValue] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[colorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comment]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comment](
	[commentId] [int] IDENTITY(1,1) NOT NULL,
	[accountId] [int] NULL,
	[shopProductId] [int] NULL,
	[created_at] [date] NULL,
	[updated_at] [date] NULL,
	[content] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[commentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[discount]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[discount](
	[discountId] [int] IDENTITY(1,1) NOT NULL,
	[shopProductId] [int] NULL,
	[discountValue] [int] NULL,
	[startDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[promotionalPrice] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[discountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[images]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[images](
	[imagesId] [int] IDENTITY(1,1) NOT NULL,
	[shopProductId] [int] NULL,
	[imageLink] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[imagesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[message]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message](
	[messageId] [int] IDENTITY(1,1) NOT NULL,
	[chatId] [int] NULL,
	[sentTime] [datetime] NULL,
	[senderId] [int] NULL,
	[content] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[messageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[totalMoney] [money] NULL,
	[name] [varchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [varchar](20) NULL,
	[orderDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderDetail]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderDetail](
	[orderDetaiId] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [int] NULL,
	[quantity] [int] NULL,
	[price] [money] NULL,
	[productItemId] [int] NULL,
	[statusId] [int] NULL,
	[description] [nvarchar](255) NULL,
	[voucherId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderDetaiId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productItem]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productItem](
	[productItemId] [int] IDENTITY(1,1) NOT NULL,
	[shopProductId] [int] NULL,
	[sizeId] [int] NULL,
	[colorId] [int] NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productLine]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productLine](
	[productLineId] [int] IDENTITY(1,1) NOT NULL,
	[productLineName] [nvarchar](255) NULL,
	[categoryId] [int] NULL,
	[brandId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productLineId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rating]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rating](
	[ratingId] [int] IDENTITY(1,1) NOT NULL,
	[shopProductId] [int] NULL,
	[userId] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[starRating] [int] NULL,
	[orderDetailId] [int] NULL,
	[commentId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ratingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[roleId] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shop]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shop](
	[shopId] [int] IDENTITY(1,1) NOT NULL,
	[shopName] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[image] [varchar](255) NULL,
	[accountBalance] [money] NULL,
	[accountId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[shopId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shopProduct]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shopProduct](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[shopId] [int] NULL,
	[productLineId] [int] NULL,
	[price] [money] NULL,
	[description] [nvarchar](255) NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[is_deleted] [bit] NULL,
	[image] [varchar](255) NULL,
	[title] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[size]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[size](
	[sizeId] [int] IDENTITY(1,1) NOT NULL,
	[sizeValue] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[sizeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[status]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[status](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[statusValue] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[fullName] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[image] [varchar](255) NULL,
	[accountBalance] [money] NULL,
	[dob] [date] NULL,
	[gender] [bit] NULL,
	[accountId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[voucherId] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](20) NULL,
	[reducedAmount] [money] NULL,
	[endDate] [datetime] NULL,
	[isGlobal] [bit] NULL,
	[description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[voucherId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucherShop]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucherShop](
	[voucherShopId] [int] IDENTITY(1,1) NOT NULL,
	[shopId] [int] NULL,
	[voucherUserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[voucherShopId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucherUser]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucherUser](
	[voucherUserId] [int] IDENTITY(1,1) NOT NULL,
	[voucherId] [int] NULL,
	[userId] [int] NULL,
	[quantity] [int] NULL,
	[isUsed] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[voucherUserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[wishlist]    Script Date: 10/07/2024 5:20:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[wishlist](
	[wishListId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[shopProductId] [int] NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[wishListId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (1, N'admin', N'mNy+OfwBVzPj9442CM86ANCQGd0=', CAST(N'2024-05-19' AS Date), NULL, 0, 1, N'0999999888', N'admin@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (2, N'user', N'VONcHg1wFhOU0vAVHAYsViNhnTU=', CAST(N'2024-05-19' AS Date), NULL, 0, 2, N'0943561200', N'khiem123@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (3, N'shop1', N'MTjz12pP83hutfr24fMHRBK0RXY=', CAST(N'2024-05-19' AS Date), NULL, 0, 3, N'0948484848', N'shop1@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (4, N'shop2', N'MUhkc4ri4TP7vOvFZ7cg+Diqvb8=', CAST(N'2024-05-23' AS Date), NULL, 0, 3, N'0984848484', N'shop2@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (5, N'khiem', N'qf8nByMUdU4+zhlp3kPB+bwO1fQ=', CAST(N'2024-06-06' AS Date), NULL, 0, 2, N'0948481235', N'khiem@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (6, N'shop3', N'6hBPVrJiazncmV8MZ09AQ9yhogE=', CAST(N'2024-06-08' AS Date), NULL, 0, 3, N'0999999999', N'shop3@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (7, N'shop4', N'6hBPVrJiazncmV8MZ09AQ9yhogE=', CAST(N'2024-06-08' AS Date), NULL, 0, 3, N'0888888888', N'shop4@gmail.com')
INSERT [dbo].[account] ([accountId], [username], [password], [created_at], [updated_at], [is_deleted], [roleId], [phone], [email]) VALUES (8, N'shop5', N'6hBPVrJiazncmV8MZ09AQ9yhogE=', CAST(N'2024-06-08' AS Date), NULL, 0, 3, N'0989898999', N'shop5@gmail.com')
SET IDENTITY_INSERT [dbo].[account] OFF
GO
SET IDENTITY_INSERT [dbo].[brand] ON 

INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (1, N'nike', N'images/nike-logo.jpg')
INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (2, N'adidas', N'images/logo-adidas.png')
INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (3, N'puma', N'images/logo-puma.png')
INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (4, N'gucci', N'images/logo-gucci.png')
INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (5, N'uniqlo', N'images/logo-uniqlo.png')
INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (6, N'thenorthface', NULL)
INSERT [dbo].[brand] ([brandId], [brandName], [image]) VALUES (7, N'nobrand', NULL)
SET IDENTITY_INSERT [dbo].[brand] OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([categoryId], [categoryName], [image]) VALUES (1, N'TShirt', N'')
INSERT [dbo].[category] ([categoryId], [categoryName], [image]) VALUES (2, N'Pants', N'')
INSERT [dbo].[category] ([categoryId], [categoryName], [image]) VALUES (3, N'SportsWear', N'')
INSERT [dbo].[category] ([categoryId], [categoryName], [image]) VALUES (4, N'Jacket', N'')
INSERT [dbo].[category] ([categoryId], [categoryName], [image]) VALUES (5, N'Shorts', N'')
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[color] ON 

INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (1, N'red')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (2, N'blue')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (3, N'black')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (4, N'white')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (5, N'brown')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (6, N'navi')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (7, N'cream')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (8, N'black-white')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (9, N'red-brown')
INSERT [dbo].[color] ([colorId], [colorValue]) VALUES (10, N'silver')
SET IDENTITY_INSERT [dbo].[color] OFF
GO
SET IDENTITY_INSERT [dbo].[comment] ON 

INSERT [dbo].[comment] ([commentId], [accountId], [shopProductId], [created_at], [updated_at], [content]) VALUES (1, 2, 4, CAST(N'2024-06-25' AS Date), NULL, N'')
SET IDENTITY_INSERT [dbo].[comment] OFF
GO
SET IDENTITY_INSERT [dbo].[images] ON 

INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (1, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (2, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (3, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (4, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (5, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (6, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (7, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (8, 1, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (9, 2, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (10, 2, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (11, 2, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (12, 2, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (13, 2, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (14, 3, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (15, 3, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (16, 3, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (17, 3, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (18, 4, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (19, 4, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (20, 4, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (21, 6, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (22, 6, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (23, 6, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (24, 6, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (25, 7, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (26, 7, N'images/DemoForTableImages.jpg')
INSERT [dbo].[images] ([imagesId], [shopProductId], [imageLink]) VALUES (27, 7, N'images/DemoForTableImages.jpg')
SET IDENTITY_INSERT [dbo].[images] OFF
GO
SET IDENTITY_INSERT [dbo].[order] ON 

INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (18, 4, 3850000.0000, NULL, N'Nghệ an', NULL, CAST(N'2024-06-16T00:00:00.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (19, 4, 8250000.0000, NULL, NULL, NULL, CAST(N'2024-06-16T00:00:00.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (20, 4, 0.0000, NULL, NULL, NULL, CAST(N'2024-06-16T00:00:00.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (21, 4, 540000.0000, NULL, NULL, NULL, CAST(N'2024-06-16T00:00:00.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (22, 4, 2750000.0000, NULL, NULL, NULL, CAST(N'2024-06-16T00:00:00.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (24, 4, 0.0000, NULL, NULL, NULL, CAST(N'2024-06-16T18:40:00.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (25, 1, 0.0000, NULL, NULL, NULL, CAST(N'2024-06-16T21:23:51.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (26, 1, 2150000.0000, N'Tran Van A', N'Ha Noi', N'0961075000', CAST(N'2024-06-17T15:51:10.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (27, 1, 2100000.0000, N'khiem', N'Hoa Lac', N'0961075000', CAST(N'2024-06-17T16:14:28.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (28, 1, 2750000.0000, N'khiem', N'Hoa Lac', N'0961075000', CAST(N'2024-06-29T16:12:46.000' AS DateTime))
INSERT [dbo].[order] ([orderId], [userId], [totalMoney], [name], [address], [phone], [orderDate]) VALUES (29, 1, 980000.0000, N'khiem', N'Hoa Lac', N'0961075000', CAST(N'2024-07-10T15:22:33.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[order] OFF
GO
SET IDENTITY_INSERT [dbo].[orderDetail] ON 

INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (25, 18, 1, 2750000.0000, 2, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (26, 18, 1, 1100000.0000, 25, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (27, 18, 1, 540000.0000, 34, 4, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (28, 18, 1, 540000.0000, 33, 4, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (29, 19, 1, 2750000.0000, 2, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (30, 19, 1, 2750000.0000, 7, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (31, 19, 1, 2750000.0000, 9, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (32, 20, 1, 540000.0000, 35, 4, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (33, 21, 1, 540000.0000, 34, 1, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (34, 22, 1, 2750000.0000, 1, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (35, 24, 1, 1700000.0000, 98, 4, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (36, 25, 1, 2100000.0000, 14, 4, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (37, 25, 1, 1100000.0000, 25, 4, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (38, 26, 1, 500000.0000, 39, 2, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (39, 26, 1, 550000.0000, 28, 3, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (40, 26, 1, 1100000.0000, 25, 2, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (41, 27, 1, 2100000.0000, 15, 1, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (42, 28, 1, 2750000.0000, 2, 1, NULL, NULL)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (43, 29, 1, 500000.0000, 39, 1, NULL, 2)
INSERT [dbo].[orderDetail] ([orderDetaiId], [orderId], [quantity], [price], [productItemId], [statusId], [description], [voucherId]) VALUES (44, 29, 1, 540000.0000, 35, 1, NULL, 2)
SET IDENTITY_INSERT [dbo].[orderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[productItem] ON 

INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (1, 1, 1, 5, 9)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (2, 1, 2, 5, 7)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (3, 1, 3, 5, 3)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (4, 1, 4, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (5, 1, 5, 5, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (6, 1, 6, 5, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (7, 1, 3, 6, 7)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (8, 1, 4, 6, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (9, 1, 5, 6, 29)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (10, 2, 3, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (11, 2, 4, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (12, 2, 5, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (13, 2, 6, 3, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (14, 2, 3, 4, 0)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (15, 2, 5, 4, 9)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (16, 2, 7, 4, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (17, 2, 8, 4, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (18, 2, 1, 7, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (19, 2, 2, 7, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (20, 2, 3, 7, 9)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (21, 2, 4, 7, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (22, 2, 7, 7, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (23, 2, 8, 7, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (24, 3, 1, 4, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (25, 3, 3, 4, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (26, 3, 4, 4, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (27, 3, 6, 4, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (28, 4, 3, 4, 38)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (29, 4, 4, 4, 28)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (30, 4, 6, 4, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (31, 6, 3, 4, 15)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (32, 6, 5, 4, 17)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (33, 6, 6, 4, 29)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (34, 7, 4, 8, 8)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (35, 7, 5, 8, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (36, 7, 6, 8, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (37, 7, 7, 8, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (38, 8, 3, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (39, 8, 4, 5, 17)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (40, 8, 5, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (41, 8, 6, 5, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (42, 9, 3, 9, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (43, 9, 4, 9, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (44, 9, 2, 9, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (45, 9, 5, 9, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (46, 9, 6, 9, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (47, 9, 7, 9, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (48, 10, 2, 9, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (49, 10, 3, 9, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (50, 10, 4, 9, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (51, 10, 5, 9, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (52, 10, 6, 9, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (53, 10, 7, 9, 50)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (54, 11, 3, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (55, 11, 4, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (56, 11, 5, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (57, 11, 6, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (58, 11, 7, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (59, 11, 8, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (60, 12, 3, 10, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (61, 12, 4, 10, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (62, 12, 5, 10, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (63, 12, 6, 10, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (64, 12, 7, 10, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (65, 12, 8, 10, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (66, 13, 3, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (67, 13, 4, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (68, 13, 5, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (69, 13, 6, 3, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (70, 13, 3, 4, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (72, 13, 4, 4, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (73, 13, 5, 4, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (74, 13, 6, 4, 40)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (75, 14, 3, 10, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (76, 14, 4, 10, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (77, 14, 5, 10, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (78, 14, 6, 10, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (79, 14, 7, 10, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (80, 15, 3, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (81, 15, 4, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (82, 15, 5, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (83, 15, 6, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (85, 15, 7, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (86, 16, 1, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (87, 16, 2, 5, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (88, 16, 3, 5, 15)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (89, 16, 4, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (90, 16, 5, 5, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (91, 16, 6, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (92, 17, 2, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (93, 17, 3, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (94, 17, 4, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (95, 17, 5, 5, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (96, 17, 6, 5, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (97, 18, 3, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (98, 18, 4, 3, 9)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (99, 18, 5, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (100, 18, 6, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (101, 18, 7, 3, 15)
GO
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (102, 19, 3, 8, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (103, 19, 4, 8, 35)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (104, 19, 5, 8, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (105, 19, 6, 8, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (106, 19, 7, 8, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (107, 20, 3, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (108, 20, 4, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (109, 20, 5, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (110, 20, 6, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (111, 20, 7, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (112, 20, 8, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (113, 23, 3, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (114, 23, 4, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (115, 23, 5, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (116, 23, 6, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (117, 23, 7, 3, 15)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (118, 23, 8, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (119, 24, 1, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (120, 24, 2, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (121, 24, 3, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (122, 24, 4, 3, 15)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (123, 24, 5, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (124, 24, 6, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (125, 25, 1, 3, 5)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (126, 25, 2, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (127, 25, 3, 3, 15)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (128, 25, 4, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (129, 25, 5, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (130, 25, 6, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (131, 25, 7, 3, 25)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (132, 26, 1, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (133, 26, 2, 3, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (134, 26, 3, 3, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (135, 26, 4, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (136, 26, 5, 3, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (137, 26, 7, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (138, 26, 8, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (139, 27, 2, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (140, 27, 3, 3, 30)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (141, 27, 4, 3, 50)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (142, 27, 5, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (143, 27, 6, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (144, 27, 7, 3, 15)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (145, 28, 2, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (146, 28, 3, 3, 10)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (147, 28, 4, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (148, 28, 5, 3, 20)
INSERT [dbo].[productItem] ([productItemId], [shopProductId], [sizeId], [colorId], [quantity]) VALUES (149, 28, 6, 3, 20)
SET IDENTITY_INSERT [dbo].[productItem] OFF
GO
SET IDENTITY_INSERT [dbo].[productLine] ON 

INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (1, N'Nike TShirt', 1, 1)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (2, N'Adidas TShirt', 1, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (3, N'Nike TShirt', 1, 1)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (4, N'SportsWear Uniqlo', 3, 5)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (5, N'SportsWear Uniqlo', 3, 5)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (6, N'Adidas TShirt', 1, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (7, N'Adidas TShirt', 1, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (8, N'Adidas TShirt', 1, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (9, N'Adidas TShirt', 1, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (10, N'Nike TShirt', 1, 1)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (11, N'Adidas TShirt', 1, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (12, N'TheNorthFace Pants', 2, 6)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (13, N'TheNorthFace Pants', 2, 6)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (14, N'NoBrand Pants', 2, 7)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (15, N'Adidas Jacket', 4, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (16, N'Adidas Shorts', 4, 5)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (18, N'SportsWear NoBrand', 3, 7)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (19, N'NoBrand Jacket', 4, 7)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (20, N'Uniqlo Jacket', 4, 5)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (21, N'NoBrand Jacket', 4, 5)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (22, N'Adidas Shorts', 5, 2)
INSERT [dbo].[productLine] ([productLineId], [productLineName], [categoryId], [brandId]) VALUES (23, N'Adidas Shorts', 5, 2)
SET IDENTITY_INSERT [dbo].[productLine] OFF
GO
SET IDENTITY_INSERT [dbo].[rating] ON 

INSERT [dbo].[rating] ([ratingId], [shopProductId], [userId], [created_at], [updated_at], [starRating], [orderDetailId], [commentId]) VALUES (1, 4, 1, CAST(N'2024-06-25T20:43:10.233' AS DateTime), NULL, 5, 39, 1)
SET IDENTITY_INSERT [dbo].[rating] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([roleId], [roleName]) VALUES (1, N'admin')
INSERT [dbo].[role] ([roleId], [roleName]) VALUES (2, N'user')
INSERT [dbo].[role] ([roleId], [roleName]) VALUES (3, N'shop')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
SET IDENTITY_INSERT [dbo].[shop] ON 

INSERT [dbo].[shop] ([shopId], [shopName], [address], [image], [accountBalance], [accountId]) VALUES (1, N'shop1', N'FPT Hòa lạc', N'', 0.0000, 3)
INSERT [dbo].[shop] ([shopId], [shopName], [address], [image], [accountBalance], [accountId]) VALUES (5, N'shop2', N'FPT Hòa lạc', N'', 0.0000, 4)
INSERT [dbo].[shop] ([shopId], [shopName], [address], [image], [accountBalance], [accountId]) VALUES (6, N'shop3', N'FPT', NULL, 0.0000, 6)
INSERT [dbo].[shop] ([shopId], [shopName], [address], [image], [accountBalance], [accountId]) VALUES (7, N'shop4', N'FPT', NULL, 0.0000, 7)
INSERT [dbo].[shop] ([shopId], [shopName], [address], [image], [accountBalance], [accountId]) VALUES (8, N'shop5', N'FPT', NULL, 0.0000, 8)
SET IDENTITY_INSERT [dbo].[shop] OFF
GO
SET IDENTITY_INSERT [dbo].[shopProduct] ON 

INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (1, 1, 1, 2750000.0000, N'descriptionDemo', CAST(N'2024-05-19T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (2, 1, 2, 2100000.0000, N'descriptionDemo', CAST(N'2024-05-19T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (3, 1, 3, 1100000.0000, N'descriptionDemo', CAST(N'2024-05-19T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (4, 5, 3, 550000.0000, N'descriptionDemo', CAST(N'2024-05-23T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (6, 5, 4, 540000.0000, N'descriptionDemo', CAST(N'2024-05-23T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (7, 5, 5, 540000.0000, N'descriptionDemo', CAST(N'2024-05-23T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (8, 1, 6, 500000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (9, 1, 7, 550000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (10, 5, 7, 1000000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (11, 5, 8, 700000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (12, 5, 9, 600000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (13, 6, 2, 2000000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (14, 6, 10, 300000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (15, 6, 11, 578000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (16, 6, 12, 700000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (17, 7, 13, 1200000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (18, 7, 14, 1700000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (19, 7, 15, 300000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (20, 7, 16, 340000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (23, 8, 18, 900000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (24, 8, 19, 420000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (25, 8, 20, 200000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (26, 8, 21, 220000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (27, 8, 22, 230000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
INSERT [dbo].[shopProduct] ([id], [shopId], [productLineId], [price], [description], [created_at], [updated_at], [is_deleted], [image], [title]) 
VALUES (28, 8, 23, 230000.0000, N'descriptionDemo', CAST(N'2024-06-08T00:00:00.000' AS DateTime), NULL, 0, N'images/imageDemo.jpg', N'titleDemo')
SET IDENTITY_INSERT [dbo].[shopProduct] OFF
GO
SET IDENTITY_INSERT [dbo].[size] ON 

INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (1, 35)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (2, 36)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (3, 37)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (4, 38)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (5, 39)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (6, 40)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (7, 41)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (8, 42)
INSERT [dbo].[size] ([sizeId], [sizeValue]) VALUES (9, 43)
SET IDENTITY_INSERT [dbo].[size] OFF
GO
SET IDENTITY_INSERT [dbo].[status] ON 

INSERT [dbo].[status] ([statusId], [statusValue]) VALUES (1, N'wait')
INSERT [dbo].[status] ([statusId], [statusValue]) VALUES (2, N'process')
INSERT [dbo].[status] ([statusId], [statusValue]) VALUES (3, N'done')
INSERT [dbo].[status] ([statusId], [statusValue]) VALUES (4, N'cancel')
SET IDENTITY_INSERT [dbo].[status] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([userId], [fullName], [address], [image], [accountBalance], [dob], [gender], [accountId]) VALUES (1, N'khiem', N'Hoa Lac', N'images/imageDemo.jpg', 0.0000, CAST(N'2024-06-14' AS Date), 1, 2)
INSERT [dbo].[user] ([userId], [fullName], [address], [image], [accountBalance], [dob], [gender], [accountId]) VALUES (4, N'khiem', N'Hoa Lac', NULL, 0.0000, CAST(N'2024-06-14' AS Date), 1, 5)
SET IDENTITY_INSERT [dbo].[user] OFF
GO
SET IDENTITY_INSERT [dbo].[voucher] ON 

INSERT [dbo].[voucher] ([voucherId], [code], [reducedAmount], [endDate], [isGlobal], [description]) VALUES (1, N'FIRSTCODE', 15000.0000, CAST(N'2025-01-01T00:00:00.000' AS DateTime), 1, N'Giảm 15000 cho 1 sản phẩm bất kì')
INSERT [dbo].[voucher] ([voucherId], [code], [reducedAmount], [endDate], [isGlobal], [description]) VALUES (2, N'SECONDCODE', 30000.0000, CAST(N'2025-01-01T00:00:00.000' AS DateTime), 1, N'Giảm 30000 cho 1 sản phẩm bất kì')
SET IDENTITY_INSERT [dbo].[voucher] OFF
GO
SET IDENTITY_INSERT [dbo].[voucherUser] ON 

INSERT [dbo].[voucherUser] ([voucherUserId], [voucherId], [userId], [quantity], [isUsed]) VALUES (1, 1, 1, 100, 0)
INSERT [dbo].[voucherUser] ([voucherUserId], [voucherId], [userId], [quantity], [isUsed]) VALUES (2, 2, 1, 100, 0)
SET IDENTITY_INSERT [dbo].[voucherUser] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__account__F3DBC572548BB252]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[account] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__chatBox__15C65EB313074DC5]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[chatBox] ADD UNIQUE NONCLUSTERED 
(
	[userId] ASC,
	[shopId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UC_ProductItem_ShopProduct_Color_Size]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[productItem] ADD  CONSTRAINT [UC_ProductItem_ShopProduct_Color_Size] UNIQUE NONCLUSTERED 
(
	[shopProductId] ASC,
	[colorId] ASC,
	[sizeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__shop__F267251FD4C00FBC]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[shop] ADD UNIQUE NONCLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__shop__F267251FF4D33C81]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[shop] ADD UNIQUE NONCLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__user__F267251F2EC5F279]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[user] ADD UNIQUE NONCLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__user__F267251F9C57364C]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[user] ADD UNIQUE NONCLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__voucher__357D4CF924171800]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[voucher] ADD UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UC_Wishlist_User_ShopProduct]    Script Date: 10/07/2024 5:20:54 CH ******/
ALTER TABLE [dbo].[wishlist] ADD  CONSTRAINT [UC_Wishlist_User_ShopProduct] UNIQUE NONCLUSTERED 
(
	[userId] ASC,
	[shopProductId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[account] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[account] ADD  DEFAULT (NULL) FOR [updated_at]
GO
ALTER TABLE [dbo].[account] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[comment] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[comment] ADD  DEFAULT (NULL) FOR [updated_at]
GO
ALTER TABLE [dbo].[discount] ADD  DEFAULT (getdate()) FOR [startDate]
GO
ALTER TABLE [dbo].[message] ADD  DEFAULT (getdate()) FOR [sentTime]
GO
ALTER TABLE [dbo].[order] ADD  DEFAULT (getdate()) FOR [orderDate]
GO
ALTER TABLE [dbo].[rating] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[rating] ADD  DEFAULT (NULL) FOR [updated_at]
GO
ALTER TABLE [dbo].[shop] ADD  DEFAULT ((0)) FOR [accountBalance]
GO
ALTER TABLE [dbo].[shopProduct] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[shopProduct] ADD  DEFAULT (NULL) FOR [updated_at]
GO
ALTER TABLE [dbo].[shopProduct] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[user] ADD  DEFAULT ((0)) FOR [accountBalance]
GO
ALTER TABLE [dbo].[user] ADD  DEFAULT ((0)) FOR [gender]
GO
ALTER TABLE [dbo].[voucher] ADD  DEFAULT ('false') FOR [isGlobal]
GO
ALTER TABLE [dbo].[voucherUser] ADD  DEFAULT ('false') FOR [isUsed]
GO
ALTER TABLE [dbo].[wishlist] ADD  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[role] ([roleId])
GO
ALTER TABLE [dbo].[chatBox]  WITH CHECK ADD FOREIGN KEY([shopId])
REFERENCES [dbo].[shop] ([shopId])
GO
ALTER TABLE [dbo].[chatBox]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD FOREIGN KEY([accountId])
REFERENCES [dbo].[account] ([accountId])
GO
ALTER TABLE [dbo].[discount]  WITH CHECK ADD FOREIGN KEY([shopProductId])
REFERENCES [dbo].[shopProduct] ([id])
GO
ALTER TABLE [dbo].[images]  WITH CHECK ADD FOREIGN KEY([shopProductId])
REFERENCES [dbo].[shopProduct] ([id])
GO
ALTER TABLE [dbo].[message]  WITH CHECK ADD FOREIGN KEY([chatId])
REFERENCES [dbo].[chatBox] ([chatId])
GO
ALTER TABLE [dbo].[message]  WITH CHECK ADD FOREIGN KEY([senderId])
REFERENCES [dbo].[account] ([accountId])
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[orderDetail]  WITH CHECK ADD FOREIGN KEY([orderId])
REFERENCES [dbo].[order] ([orderId])
GO
ALTER TABLE [dbo].[orderDetail]  WITH CHECK ADD FOREIGN KEY([productItemId])
REFERENCES [dbo].[productItem] ([productItemId])
GO
ALTER TABLE [dbo].[orderDetail]  WITH CHECK ADD FOREIGN KEY([voucherId])
REFERENCES [dbo].[voucher] ([voucherId])
GO
ALTER TABLE [dbo].[orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_order_detail_statusId] FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
ALTER TABLE [dbo].[orderDetail] CHECK CONSTRAINT [FK_order_detail_statusId]
GO
ALTER TABLE [dbo].[productItem]  WITH CHECK ADD FOREIGN KEY([colorId])
REFERENCES [dbo].[color] ([colorId])
GO
ALTER TABLE [dbo].[productItem]  WITH CHECK ADD FOREIGN KEY([shopProductId])
REFERENCES [dbo].[shopProduct] ([id])
GO
ALTER TABLE [dbo].[productItem]  WITH CHECK ADD FOREIGN KEY([sizeId])
REFERENCES [dbo].[size] ([sizeId])
GO
ALTER TABLE [dbo].[productLine]  WITH CHECK ADD FOREIGN KEY([brandId])
REFERENCES [dbo].[brand] ([brandId])
GO
ALTER TABLE [dbo].[productLine]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[category] ([categoryId])
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD FOREIGN KEY([shopProductId])
REFERENCES [dbo].[shopProduct] ([id])
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD  CONSTRAINT [FK_rating_comment] FOREIGN KEY([commentId])
REFERENCES [dbo].[comment] ([commentId])
GO
ALTER TABLE [dbo].[rating] CHECK CONSTRAINT [FK_rating_comment]
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD  CONSTRAINT [FK_rating_orderDetail] FOREIGN KEY([orderDetailId])
REFERENCES [dbo].[orderDetail] ([orderDetaiId])
GO
ALTER TABLE [dbo].[rating] CHECK CONSTRAINT [FK_rating_orderDetail]
GO
ALTER TABLE [dbo].[shop]  WITH CHECK ADD FOREIGN KEY([accountId])
REFERENCES [dbo].[account] ([accountId])
GO
ALTER TABLE [dbo].[shopProduct]  WITH CHECK ADD FOREIGN KEY([productLineId])
REFERENCES [dbo].[productLine] ([productLineId])
GO
ALTER TABLE [dbo].[shopProduct]  WITH CHECK ADD FOREIGN KEY([shopId])
REFERENCES [dbo].[shop] ([shopId])
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD FOREIGN KEY([accountId])
REFERENCES [dbo].[account] ([accountId])
GO
ALTER TABLE [dbo].[voucherShop]  WITH CHECK ADD FOREIGN KEY([shopId])
REFERENCES [dbo].[shop] ([shopId])
GO
ALTER TABLE [dbo].[voucherShop]  WITH CHECK ADD  CONSTRAINT [FK_voucherShop_voucherUser] FOREIGN KEY([voucherUserId])
REFERENCES [dbo].[voucherUser] ([voucherUserId])
GO
ALTER TABLE [dbo].[voucherShop] CHECK CONSTRAINT [FK_voucherShop_voucherUser]
GO
ALTER TABLE [dbo].[voucherUser]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[voucherUser]  WITH CHECK ADD FOREIGN KEY([voucherId])
REFERENCES [dbo].[voucher] ([voucherId])
GO
ALTER TABLE [dbo].[wishlist]  WITH CHECK ADD FOREIGN KEY([shopProductId])
REFERENCES [dbo].[shopProduct] ([id])
GO
ALTER TABLE [dbo].[wishlist]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD CHECK  (([starRating]>=(1) AND [starRating]<=(5)))
GO
USE [master]
GO
ALTER DATABASE [SWP_G4] SET  READ_WRITE 
GO
