USE [master]
GO
/****** Object:  Database [stationery]    Script Date: 7/25/2023 9:21:14 AM ******/
CREATE DATABASE [stationery]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'stationery', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.NGUYENTUNG\MSSQL\DATA\stationery.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'stationery_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.NGUYENTUNG\MSSQL\DATA\stationery_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [stationery] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [stationery].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [stationery] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [stationery] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [stationery] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [stationery] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [stationery] SET ARITHABORT OFF 
GO
ALTER DATABASE [stationery] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [stationery] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [stationery] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [stationery] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [stationery] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [stationery] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [stationery] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [stationery] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [stationery] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [stationery] SET  DISABLE_BROKER 
GO
ALTER DATABASE [stationery] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [stationery] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [stationery] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [stationery] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [stationery] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [stationery] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [stationery] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [stationery] SET RECOVERY FULL 
GO
ALTER DATABASE [stationery] SET  MULTI_USER 
GO
ALTER DATABASE [stationery] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [stationery] SET DB_CHAINING OFF 
GO
ALTER DATABASE [stationery] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [stationery] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [stationery] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [stationery] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'stationery', N'ON'
GO
ALTER DATABASE [stationery] SET QUERY_STORE = OFF
GO
USE [stationery]
GO
/****** Object:  Table [dbo].[chitietdonhang]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chitietdonhang](
	[idchitietdonhang] [int] IDENTITY(1,1) NOT NULL,
	[iddonhang] [int] NULL,
	[idsanpham] [int] NULL,
	[soluongban] [int] NULL,
	[giaban] [int] NULL,
 CONSTRAINT [PK_ChiTietDonHang] PRIMARY KEY CLUSTERED 
(
	[idchitietdonhang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[donhang]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[donhang](
	[iddonhang] [int] IDENTITY(1,1) NOT NULL,
	[idkhachhang] [int] NULL,
	[ngaytao] [date] NULL,
	[tongtien] [int] NULL,
 CONSTRAINT [PK_DonHang] PRIMARY KEY CLUSTERED 
(
	[iddonhang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[giohang]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[giohang](
	[idgiohang] [int] IDENTITY(1,1) NOT NULL,
	[idkhachhang] [int] NULL,
	[ngaytao] [date] NULL,
 CONSTRAINT [PK_giohang] PRIMARY KEY CLUSTERED 
(
	[idgiohang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[khachhang]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[khachhang](
	[idkhachhang] [int] IDENTITY(1,1) NOT NULL,
	[tendangnhap] [varchar](50) NULL,
	[matkhau] [nvarchar](50) NULL,
	[hoten] [nvarchar](50) NULL,
	[diachi] [nvarchar](max) NULL,
	[sdt] [nchar](10) NULL,
	[vaitro] [bit] NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[idkhachhang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kho]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kho](
	[IDKho] [int] IDENTITY(1,1) NOT NULL,
	[IDSanPham] [nchar](10) NULL,
	[SoLuong] [int] NULL,
	[GiaNhap] [int] NULL,
 CONSTRAINT [PK_Kho] PRIMARY KEY CLUSTERED 
(
	[IDKho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nhasanxuat]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nhasanxuat](
	[idnhasx] [int] IDENTITY(1,1) NOT NULL,
	[tennhasx] [nvarchar](50) NULL,
	[diachi] [nvarchar](max) NULL,
	[sdt] [nvarchar](10) NULL,
 CONSTRAINT [PK_NhaSanXuat] PRIMARY KEY CLUSTERED 
(
	[idnhasx] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phanloai]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phanloai](
	[idphanloai] [int] IDENTITY(1,1) NOT NULL,
	[tenphanloai] [nvarchar](50) NULL,
 CONSTRAINT [PK_PhanLoai] PRIMARY KEY CLUSTERED 
(
	[idphanloai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phanloaisanpham]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phanloaisanpham](
	[idphanloaisanpham] [int] IDENTITY(1,1) NOT NULL,
	[idphanloai] [int] NULL,
	[idsanpham] [int] NULL,
	[TenSP] [nvarchar](50) NULL,
	[DonGia] [int] NULL,
 CONSTRAINT [PK_PhanLoaiSanPham] PRIMARY KEY CLUSTERED 
(
	[idphanloaisanpham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 7/25/2023 9:21:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[idsanpham] [int] IDENTITY(1,1) NOT NULL,
	[tensp] [nvarchar](50) NULL,
	[idphanloai] [int] NULL,
	[ngaynhap] [date] NULL,
	[dongia] [nchar](20) NULL,
	[idnhasx] [int] NULL,
	[infosanpham] [nvarchar](max) NULL,
	[hinh] [nvarchar](max) NULL,
	[tacgia] [nvarchar](70) NULL,
	[hinhthucbia] [nvarchar](50) NULL,
	[nhacungcap] [nvarchar](70) NULL,
	[nhaxuatban] [nvarchar](50) NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[idsanpham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[chitietdonhang] ON 

INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1, NULL, NULL, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (2, NULL, NULL, 2, 378000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3, NULL, NULL, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (4, NULL, NULL, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (5, NULL, NULL, 1, 189000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (6, NULL, NULL, 1, 18000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (7, NULL, NULL, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (8, NULL, NULL, 1, 189000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (9, NULL, NULL, 1, 18000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (10, NULL, NULL, 2, 28000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1002, NULL, NULL, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1003, NULL, NULL, 2, 500000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1004, NULL, NULL, 2, 36000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1005, NULL, NULL, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1006, NULL, NULL, 1, 359000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1007, NULL, NULL, 2, 36000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1008, NULL, NULL, 2, 500000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (1009, NULL, NULL, 1, 359000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (2002, NULL, NULL, 2, 500000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3002, NULL, NULL, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3003, NULL, NULL, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3004, 5008, NULL, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3005, 5009, NULL, 1, 18000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3006, 5009, NULL, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3007, 5009, NULL, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3008, 5009, NULL, 1, 359000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3010, 5011, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3011, 5011, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3012, 5012, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3013, 5012, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3014, 5013, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3015, 5013, 2022, 1, 359000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3016, 5014, 1, 1, 18000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3017, 5014, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3018, 5015, 1, 1, 18000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3019, 5015, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3020, 5016, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3021, 5016, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3022, 5017, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3023, 5017, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3024, 5018, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3025, 5018, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3026, 5019, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3027, 5019, 1028, 1, 250000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3028, 5020, 2, 1, 14000)
INSERT [dbo].[chitietdonhang] ([idchitietdonhang], [iddonhang], [idsanpham], [soluongban], [giaban]) VALUES (3029, 5020, 1028, 1, 250000)
SET IDENTITY_INSERT [dbo].[chitietdonhang] OFF
GO
SET IDENTITY_INSERT [dbo].[donhang] ON 

INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5, 1004, CAST(N'2023-06-14' AS Date), 261000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (6, 1004, CAST(N'2023-06-14' AS Date), 797000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (7, 1004, CAST(N'2023-06-15' AS Date), 32000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (1007, 1004, CAST(N'2023-06-15' AS Date), 412000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (1008, 4008, CAST(N'2023-06-15' AS Date), 28000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (1009, 4008, CAST(N'2023-06-15' AS Date), 46000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (1010, 1004, CAST(N'2023-06-15' AS Date), 32000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (1011, 1004, CAST(N'2023-06-15' AS Date), 74000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (2007, 1004, CAST(N'2023-06-19' AS Date), 14000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (2008, 1004, CAST(N'2023-06-19' AS Date), 642000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (2009, 1004, CAST(N'2023-06-19' AS Date), 221000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (2010, 1004, CAST(N'2023-06-19' AS Date), 235000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (3007, 1004, CAST(N'2023-06-21' AS Date), 250000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (3008, 1004, CAST(N'2023-06-22' AS Date), 500000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (3009, 8009, CAST(N'2023-06-22' AS Date), 645000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (3010, 8009, CAST(N'2023-06-22' AS Date), 895000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (4007, 9008, CAST(N'2023-06-26' AS Date), 500000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5007, 1004, CAST(N'2023-07-18' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5008, 1004, CAST(N'2023-07-18' AS Date), 14000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5009, 1004, CAST(N'2023-07-18' AS Date), 641000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5010, 1004, CAST(N'2023-07-18' AS Date), 623000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5011, 1004, CAST(N'2023-07-18' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5012, 1004, CAST(N'2023-07-19' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5013, 1004, CAST(N'2023-07-19' AS Date), 609000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5014, 1004, CAST(N'2023-07-19' AS Date), 32000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5015, 1004, CAST(N'2023-07-19' AS Date), 32000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5016, 1004, CAST(N'2023-07-19' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5017, 1004, CAST(N'2023-07-19' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5018, 1004, CAST(N'2023-07-19' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5019, 1004, CAST(N'2023-07-19' AS Date), 264000)
INSERT [dbo].[donhang] ([iddonhang], [idkhachhang], [ngaytao], [tongtien]) VALUES (5020, 1004, CAST(N'2023-07-19' AS Date), 264000)
SET IDENTITY_INSERT [dbo].[donhang] OFF
GO
SET IDENTITY_INSERT [dbo].[giohang] ON 

INSERT [dbo].[giohang] ([idgiohang], [idkhachhang], [ngaytao]) VALUES (1, 1004, CAST(N'2023-06-13' AS Date))
INSERT [dbo].[giohang] ([idgiohang], [idkhachhang], [ngaytao]) VALUES (2, 1004, CAST(N'2023-06-13' AS Date))
INSERT [dbo].[giohang] ([idgiohang], [idkhachhang], [ngaytao]) VALUES (3, 1004, CAST(N'2023-06-13' AS Date))
SET IDENTITY_INSERT [dbo].[giohang] OFF
GO
SET IDENTITY_INSERT [dbo].[khachhang] ON 

INSERT [dbo].[khachhang] ([idkhachhang], [tendangnhap], [matkhau], [hoten], [diachi], [sdt], [vaitro]) VALUES (1004, N'daonguyentung2003@gmail.com', N'123', N'Đào Nguyên Tùng', N'Cần Thơ', N'0829125832', 0)
INSERT [dbo].[khachhang] ([idkhachhang], [tendangnhap], [matkhau], [hoten], [diachi], [sdt], [vaitro]) VALUES (1005, N'Admin', N'123', N'Đào Nguyên Tùng', N'Hà Nội', N'0898541825', 1)
INSERT [dbo].[khachhang] ([idkhachhang], [tendangnhap], [matkhau], [hoten], [diachi], [sdt], [vaitro]) VALUES (7010, N'daonguyentung155@gmail.com', N'123', N'Trương Văn Mai', N'Vũng Tàu', N'0912398745', 0)
INSERT [dbo].[khachhang] ([idkhachhang], [tendangnhap], [matkhau], [hoten], [diachi], [sdt], [vaitro]) VALUES (8009, N'tminhtrieu203@gmail.com', N'123', N'tranminhtriu', N'can tho', N'0829125832', 0)
INSERT [dbo].[khachhang] ([idkhachhang], [tendangnhap], [matkhau], [hoten], [diachi], [sdt], [vaitro]) VALUES (9008, N'daonguyentung333@gmail.com', N'123', N'Tran van a', N'can tho', N'0829125832', 0)
SET IDENTITY_INSERT [dbo].[khachhang] OFF
GO
SET IDENTITY_INSERT [dbo].[nhasanxuat] ON 

INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (1, N'Thiên Long', N'Vũng Tàu', N'0829125444')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (2, N'Vạn Phát', N'Hải Phòng', N'0136895715')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (6, N'Hàng Châu', N'Quảng Ninh', N'0292895287')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (7, N'Vĩnh Lộc', N'Đồng Nai', N'0595897115')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (8, N'Tuần Châu', N'Vĩnh Long', N'0989798841')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (9, N'Đàm Phát', N'Vũng Tàu', N'0979797774')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (1009, NULL, NULL, NULL)
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (1010, N'a', N'Vũng Tàu', N'0829125444')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (2006, N'Hưng Thịnh', N'can tho', N'0829125832')
INSERT [dbo].[nhasanxuat] ([idnhasx], [tennhasx], [diachi], [sdt]) VALUES (2007, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[nhasanxuat] OFF
GO
SET IDENTITY_INSERT [dbo].[phanloai] ON 

INSERT [dbo].[phanloai] ([idphanloai], [tenphanloai]) VALUES (1, N'Bút')
INSERT [dbo].[phanloai] ([idphanloai], [tenphanloai]) VALUES (2, N'Sách')
INSERT [dbo].[phanloai] ([idphanloai], [tenphanloai]) VALUES (3, N'Sách Giáo Khoa')
INSERT [dbo].[phanloai] ([idphanloai], [tenphanloai]) VALUES (4, N'Balo')
INSERT [dbo].[phanloai] ([idphanloai], [tenphanloai]) VALUES (5, N'Đồ điện')
INSERT [dbo].[phanloai] ([idphanloai], [tenphanloai]) VALUES (6, N'Truyện')
SET IDENTITY_INSERT [dbo].[phanloai] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([idsanpham], [tensp], [idphanloai], [ngaynhap], [dongia], [idnhasx], [infosanpham], [hinh], [tacgia], [hinhthucbia], [nhacungcap], [nhaxuatban]) VALUES (1, N'Bút đen', 1, CAST(N'2023-06-13' AS Date), N'18000               ', 1, N'Bút đen loại tốt nhất', N'butden.jpg', N'Hải Đăng', N'Viết', N'Fahasa Cần Thơ', NULL)
INSERT [dbo].[product] ([idsanpham], [tensp], [idphanloai], [ngaynhap], [dongia], [idnhasx], [infosanpham], [hinh], [tacgia], [hinhthucbia], [nhacungcap], [nhaxuatban]) VALUES (2, N'Bút đỏ', 1, CAST(N'2023-06-13' AS Date), N'14000               ', 1, N'Bút đỏ loại tốt nhất', N'butdo.jpg', N'Hải Đăng', N'Viết', N'Fahasa Cần Thơ', NULL)
INSERT [dbo].[product] ([idsanpham], [tensp], [idphanloai], [ngaynhap], [dongia], [idnhasx], [infosanpham], [hinh], [tacgia], [hinhthucbia], [nhacungcap], [nhaxuatban]) VALUES (1028, N'Sách giáo khoa 12', 3, CAST(N'2023-06-16' AS Date), N'250000              ', 1, N'a', N'a3b0aef3-2315-4185-84b4-3a29d806082e_sgkcap3.jpg', NULL, NULL, NULL, NULL)
INSERT [dbo].[product] ([idsanpham], [tensp], [idphanloai], [ngaynhap], [dongia], [idnhasx], [infosanpham], [hinh], [tacgia], [hinhthucbia], [nhacungcap], [nhaxuatban]) VALUES (2022, N'Balo', 4, CAST(N'2023-06-18' AS Date), N'359000              ', 1, N'balo đi học', N'5a3ac2c3-72b7-4cb7-967e-968ee611f054_tuivai.jpg', NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[product] OFF
GO
ALTER TABLE [dbo].[chitietdonhang]  WITH CHECK ADD  CONSTRAINT [FK_chitietdonhang_donhang1] FOREIGN KEY([iddonhang])
REFERENCES [dbo].[donhang] ([iddonhang])
GO
ALTER TABLE [dbo].[chitietdonhang] CHECK CONSTRAINT [FK_chitietdonhang_donhang1]
GO
ALTER TABLE [dbo].[chitietdonhang]  WITH CHECK ADD  CONSTRAINT [FK_chitietdonhang_product1] FOREIGN KEY([idsanpham])
REFERENCES [dbo].[product] ([idsanpham])
GO
ALTER TABLE [dbo].[chitietdonhang] CHECK CONSTRAINT [FK_chitietdonhang_product1]
GO
ALTER TABLE [dbo].[giohang]  WITH CHECK ADD  CONSTRAINT [FK_giohang_khachhang1] FOREIGN KEY([idkhachhang])
REFERENCES [dbo].[khachhang] ([idkhachhang])
GO
ALTER TABLE [dbo].[giohang] CHECK CONSTRAINT [FK_giohang_khachhang1]
GO
ALTER TABLE [dbo].[phanloaisanpham]  WITH CHECK ADD  CONSTRAINT [FK_phanloaisanpham_phanloai1] FOREIGN KEY([idphanloai])
REFERENCES [dbo].[phanloai] ([idphanloai])
GO
ALTER TABLE [dbo].[phanloaisanpham] CHECK CONSTRAINT [FK_phanloaisanpham_phanloai1]
GO
ALTER TABLE [dbo].[phanloaisanpham]  WITH CHECK ADD  CONSTRAINT [FK_phanloaisanpham_product] FOREIGN KEY([idsanpham])
REFERENCES [dbo].[product] ([idsanpham])
GO
ALTER TABLE [dbo].[phanloaisanpham] CHECK CONSTRAINT [FK_phanloaisanpham_product]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK_product_nhasanxuat] FOREIGN KEY([idnhasx])
REFERENCES [dbo].[nhasanxuat] ([idnhasx])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK_product_nhasanxuat]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK_product_phanloai] FOREIGN KEY([idphanloai])
REFERENCES [dbo].[phanloai] ([idphanloai])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK_product_phanloai]
GO
USE [master]
GO
ALTER DATABASE [stationery] SET  READ_WRITE 
GO
