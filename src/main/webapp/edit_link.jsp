<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.quicklinks.model.User" %>
<%@ page import="com.quicklinks.model.ShortLink" %>
<%@ page import="com.quicklinks.dao.ShortLinkDAO" %>

<%
    User user = (session != null) ? (User) session.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("auth/login.jsp");
        return;
    }

    String linkIdStr = request.getParameter("id");
    if (linkIdStr == null || linkIdStr.isEmpty()) {
        response.sendRedirect("dashboard.jsp");
        return;
    }

    ShortLinkDAO linkDAO = new ShortLinkDAO();
    ShortLink link = linkDAO.getById(Integer.parseInt(linkIdStr));

    if (link == null || link.getUserId() != user.getId()) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-body">
                    <h3 class="card-title text-center">Edit Short Link</h3>
                    <form action="<%= request.getContextPath() %>/editShortLink" method="post">
                        <input type="hidden" name="link_id" value="<%= link.getId() %>">
                        <div class="mb-3">
                            <label for="original_url" class="form-label">Original URL</label>
                            <input type="url" class="form-control" id="original_url" name="original_url" value="<%= link.getOriginalUrl() %>" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Update Link</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
