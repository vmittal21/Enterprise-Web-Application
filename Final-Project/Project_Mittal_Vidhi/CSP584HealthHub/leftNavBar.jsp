<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!-- Navigation -->
    <nav
      class="navbar justify-content-center navbar-light fixed-top fixed-top-2"
    >
      <ul class="nav">
        <li class="nav-item px-3">
          <a class="nav-link active" href="index.jsp"
            ><i class="fas fa-home"></i> Home</a
          >
        </li>
        <li class="nav-item px-3">
          <a class="nav-link active" href="findDoctor.jsp"
            ><i class="fas fa-calendar-alt"></i> Schedule an Appointment</a
          >
        </li>
        <li class="nav-item px-3">
          <a class="nav-link" href="DoctorServlet"
            ><i class="fas fa-stethoscope"></i> Doctors</a
          >
        </li>
        <li class="nav-item px-3">
          <a class="nav-link" href="medical-service.jsp"
            ><i class="far fa-compass"></i> Medical Services</a
          >
        </li>
        <li class="nav-item dropdown px-3">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
            ><i class="fas fa-shopping-bag"></i>
            Product
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="ListProduct?type=vitamin">Vitamins</a>
            <a class="dropdown-item" href="ListProduct?type=medicine">Medicine</a>
            <a class="dropdown-item" href="ListProduct?type=personalcare">Personal Care</a>
            <a class="dropdown-item" href="ListProduct?type=homecare">Home Care</a>
            <a class="dropdown-item" href="ListProduct?type=nutrition">Nutrition</a>
          </div>
        </li>
      </ul>
    </nav>