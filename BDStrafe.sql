USE [master]
GO
/****** Object:  Database [BDStrafe]    Script Date: 5/10/2024 06:06:07 ******/
CREATE DATABASE [BDStrafe]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BDStrafe', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BDStrafe.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BDStrafe_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BDStrafe_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [BDStrafe] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BDStrafe].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BDStrafe] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BDStrafe] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BDStrafe] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BDStrafe] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BDStrafe] SET ARITHABORT OFF 
GO
ALTER DATABASE [BDStrafe] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BDStrafe] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BDStrafe] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BDStrafe] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BDStrafe] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BDStrafe] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BDStrafe] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BDStrafe] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BDStrafe] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BDStrafe] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BDStrafe] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BDStrafe] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BDStrafe] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BDStrafe] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BDStrafe] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BDStrafe] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BDStrafe] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BDStrafe] SET RECOVERY FULL 
GO
ALTER DATABASE [BDStrafe] SET  MULTI_USER 
GO
ALTER DATABASE [BDStrafe] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BDStrafe] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BDStrafe] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BDStrafe] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BDStrafe] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BDStrafe] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BDStrafe', N'ON'
GO
ALTER DATABASE [BDStrafe] SET QUERY_STORE = ON
GO
ALTER DATABASE [BDStrafe] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [BDStrafe]
GO
/****** Object:  User [strafe]    Script Date: 5/10/2024 06:06:07 ******/
CREATE USER [strafe] FOR LOGIN [strafe] WITH DEFAULT_SCHEMA=[strafe]
GO
/****** Object:  User [admin]    Script Date: 5/10/2024 06:06:07 ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [strafe]
GO
ALTER ROLE [db_owner] ADD MEMBER [admin]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [admin]
GO
ALTER ROLE [db_datareader] ADD MEMBER [admin]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [admin]
GO
ALTER ROLE [db_denydatareader] ADD MEMBER [admin]
GO
ALTER ROLE [db_denydatawriter] ADD MEMBER [admin]
GO
/****** Object:  Schema [strafe]    Script Date: 5/10/2024 06:06:07 ******/
CREATE SCHEMA [strafe]
GO
/****** Object:  Table [dbo].[CarritosCompras]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CarritosCompras](
	[CarritoID] [int] IDENTITY(1,1) NOT NULL,
	[UsuarioID] [int] NULL,
	[FechaCreacion] [datetime] NULL,
	[Estado] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[CarritoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categorias]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categorias](
	[CategoriaID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoriaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetallesCarritoCompras]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetallesCarritoCompras](
	[DetalleCarritoID] [int] IDENTITY(1,1) NOT NULL,
	[CarritoID] [int] NULL,
	[ProductoID] [int] NULL,
	[Cantidad] [int] NOT NULL,
	[Precio] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DetalleCarritoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MetodosPago]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MetodosPago](
	[MetodoPagoID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MetodoPagoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pagos]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pagos](
	[PagoID] [int] IDENTITY(1,1) NOT NULL,
	[CarritoID] [int] NULL,
	[MetodoPagoID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[PagoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Productos]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Productos](
	[ProductoID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[Descripcion] [varchar](255) NULL,
	[Precio] [decimal](10, 2) NOT NULL,
	[Talla] [varchar](10) NULL,
	[Stock] [int] NOT NULL,
	[CategoriaID] [int] NULL,
	[ImagenURL] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rol]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rol](
	[RolID] [int] IDENTITY(1,1) NOT NULL,
	[NombreRol] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RolID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 5/10/2024 06:06:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuarios](
	[UsuarioID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[correo_electronico] [varchar](100) NOT NULL,
	[Contrasena] [varchar](255) NOT NULL,
	[Direccion] [varchar](255) NULL,
	[Telefono] [varchar](20) NULL,
	[RolID] [int] NULL,
	[fecha_registro] [datetime] NULL,
	[Login] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[UsuarioID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[correo_electronico] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CarritosCompras] ADD  DEFAULT (getdate()) FOR [FechaCreacion]
GO
ALTER TABLE [dbo].[Usuarios] ADD  DEFAULT (getdate()) FOR [fecha_registro]
GO
ALTER TABLE [dbo].[CarritosCompras]  WITH CHECK ADD FOREIGN KEY([UsuarioID])
REFERENCES [dbo].[Usuarios] ([UsuarioID])
GO
ALTER TABLE [dbo].[DetallesCarritoCompras]  WITH CHECK ADD FOREIGN KEY([CarritoID])
REFERENCES [dbo].[CarritosCompras] ([CarritoID])
GO
ALTER TABLE [dbo].[DetallesCarritoCompras]  WITH CHECK ADD FOREIGN KEY([ProductoID])
REFERENCES [dbo].[Productos] ([ProductoID])
GO
ALTER TABLE [dbo].[Pagos]  WITH CHECK ADD FOREIGN KEY([CarritoID])
REFERENCES [dbo].[CarritosCompras] ([CarritoID])
GO
ALTER TABLE [dbo].[Pagos]  WITH CHECK ADD FOREIGN KEY([MetodoPagoID])
REFERENCES [dbo].[MetodosPago] ([MetodoPagoID])
GO
ALTER TABLE [dbo].[Productos]  WITH CHECK ADD FOREIGN KEY([CategoriaID])
REFERENCES [dbo].[Categorias] ([CategoriaID])
GO
ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD FOREIGN KEY([RolID])
REFERENCES [dbo].[Rol] ([RolID])
GO
USE [master]
GO
ALTER DATABASE [BDStrafe] SET  READ_WRITE 
GO
