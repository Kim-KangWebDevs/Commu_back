<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<nav class="nav">
	<ul class ="navCard">
        <li class = "nav-item">
            <a href="/main" class="nav-link">
                <span class ="linkTest">main</span>
            </a>
            <a href=""></a>
        </li>
        <li class = "nav-item">
            <a href="/user?pageNum=1&pageSiz=10">
                <span  class ="linkTest">user</span>
            </a>
        </li>
        <li class = "nav-item">
            <a href="/boder">
                <span  class ="linkTest">boder</span>
            </a>
        </li>
        <li class = "nav-item">
            <a href="/logout">
                <span  class ="linkTest">Logout</span>
            </a>
        </li>
	</ul>
</nav>
</html>