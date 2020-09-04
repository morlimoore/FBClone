<%@ page import="java.util.List" %>
<%@ page import="com.morlimoore.models.Post" %>
<%@ page import="com.morlimoore.DAO.UserDetailsDAO" %>
<%@ page import="com.morlimoore.models.User" %>
<%@ page import="com.morlimoore.DAO.PostsDAO" %>
<%@ page import="com.morlimoore.models.Comment" %>
<%@ page import="com.morlimoore.DAO.CommentsDAO" %><%--
  Created by IntelliJ IDEA.
  User: Morlimoore
  Date: 9/1/20
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Facebook Theme Demo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="assets/css/facebook.css" rel="stylesheet">

    <style>
        html, body {
            height: 100%;
            font-family:verdana,arial,sans-serif;
            color:#555555;
        }

        .nav {
            font-family:Arial,sans-serif;
            font-size:13px;
        }

        a {
            color:#222222;
        }

        a:hover {
            text-decoration:none;
        }

        hr {
            border-color:#dedede;
        }

        .wrapper, .row {
            height: 100%;
            margin-left:0;
            margin-right:0;
        }

        .wrapper:before, .wrapper:after,
        .column:before, .column:after {
            content: "";
            display: table;
        }

        .wrapper:after,
        .column:after {
            clear: both;
        }

        .column {
            height: 100%;
            overflow: auto;
            *zoom:1;
        }

        .column .padding {
            padding: 20px;
        }

        .full{
            padding-top:70px;
        }

        .box {
            bottom: 0; /* increase for footer use */
            left: 0;
            position: absolute;
            right: 0;
            top: 0;
            background-color: #444444;
        }


        .divider {
            margin-top:32px;
        }

        .navbar-blue {
            border-width:0;
            background-color:#3B5999;
            color:#ffffff;
            font-family:arial,sans-serif;
            top:0;
            position:fixed;
            width:inherit;
        }

        .navbar-blue li > a,.navbar-toggle  {
            color:#efefef;
        }

        #main {
            background-color:#e9eaed;
            padding-left:0;
            padding-right:0;
        }
        #main .img-circle {
            margin-top:18px;
            height:70px;
            width:70px;
        }

        #sidebar {
            padding:0px;
            padding-top:15px;
        }

        #sidebar, #sidebar a, #sidebar-footer a {
            color:#ffffff;
            background-color:transparent;
            text-shadow:0 0 2px #000000;
            padding-left:5px;
        }
        #sidebar .nav li>a:hover {
            background-color:#393939;
        }

        .logo {
            display:block;
            padding:3px;
            background-color:#fff;
            color:#3B5999;
            height:28px;
            width:28px;
            margin:9px;
            margin-right:2px;
            margin-left:15px;
            font-size:20px;
            font-weight:700;
            text-align:center;
            text-decoration:none;
            text-shadow:0 0 1px;
            border-radius:2px;
        }
        #sidebar-footer {
            background-color:#444;
            position:absolute;
            bottom:5px;
            padding:5px;
        }
        #footer {
            margin-bottom:20px;
        }

        /* bootstrap overrides */

        h1,h2,h3 {
            font-weight:800;
        }

        .navbar-toggle, .close {
            outline:0;
        }

        .navbar-toggle .icon-bar {
            background-color: #fff;
        }

        .btn-primary,.label-primary,.list-group-item.active, .list-group-item.active:hover, .list-group-item.active:focus  {
            background-color:#3B5999;
            color:#fffffe;
        }
        .btn-default {
            color:#666666;
            text-shadow:0 0 1px rgba(0,0,0,.3);
        }
        .form-control {

        }

        .panel textarea, .well textarea, textarea.form-control
        {
            resize: none;
        }

        .badge{
            color:#3B5999;
            background-color:#fff;
        }

        .panel-default .panel-heading {
            background-color:#f9fafb;
            color:#555555;
        }

        .col-sm-9.full {
            width: 100%;
        }

        .modal-footer i, .well i {
            font-size:20px;
            color:#c0c0c0;
        }

        /* adjust the contents on smaller devices */
        @media (max-width: 768px) {

            .column .padding {
                padding: 7px;
            }

            .full{
                padding-top:20px;
            }

            .navbar-blue {
                background-color:#3B5999;
                top:0;
                width:100%;
                position:relative;
            }

        }

        /*
 * off canvas sidebar
 * --------------------------------------------------
 */
        @media screen and (max-width: 768px) {
            .row-offcanvas {
                position: relative;
                -webkit-transition: all 0.25s ease-out;
                -moz-transition: all 0.25s ease-out;
                transition: all 0.25s ease-out;
            }

            #sidebar, #sidebar a, #sidebar-footer a {
                padding-left:3px;
            }
        }

    </style>

</head>

<body>

<div class="wrapper">
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left">

            <!-- sidebar -->
            <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">

                <ul class="nav">
                    <li><a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
                </ul>

                <ul class="nav hidden-xs" id="lg-menu">
                    <li class="active"><a href="#featured"><i class="glyphicon glyphicon-list-alt"></i> Featured</a></li>
                    <li><a href="#stories"><i class="glyphicon glyphicon-list"></i> Stories</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-paperclip"></i> Saved</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-refresh"></i> Refresh</a></li>
                </ul>
                <ul class="list-unstyled hidden-xs" id="sidebar-footer">
                    <li>
                        <a href="http://usebootstrap.com/theme/facebook"><h3>Bootstrap</h3> <i class="glyphicon glyphicon-heart-empty"></i> Bootply</a>
                    </li>
                </ul>

                <!-- tiny only nav-->
                <ul class="nav visible-xs" id="xs-menu">
                    <li><a href="#featured" class="text-center"><i class="glyphicon glyphicon-list-alt"></i></a></li>
                    <li><a href="#stories" class="text-center"><i class="glyphicon glyphicon-list"></i></a></li>
                    <li><a href="#" class="text-center"><i class="glyphicon glyphicon-paperclip"></i></a></li>
                    <li><a href="#" class="text-center"><i class="glyphicon glyphicon-refresh"></i></a></li>
                </ul>

            </div>
            <!-- /sidebar -->

            <!-- main right col -->
            <div class="column col-sm-10 col-xs-11" id="main">

                <!-- top nav -->
                <div class="navbar navbar-blue navbar-static-top">
                    <div class="navbar-header">
                        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="http://usebootstrap.com/theme/facebook" class="navbar-brand logo">b</a>
                    </div>
                    <nav class="collapse navbar-collapse" role="navigation">
                        <form class="navbar-form navbar-left">
                            <div class="input-group input-group-sm" style="max-width:360px;">
                                <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                        </form>
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-home"></i> Home</a>
                            </li>
                            <li>
                                <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a>
                            </li>
                            <li>
                                <a href="#"><span class="badge">badge</span></a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a href="">More</a></li>
                                    <li><a href="">More</a></li>
                                    <li><a href="">More</a></li>
                                    <li><a href="">More</a></li>
                                    <li><a href="">More</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
                <!-- /top nav -->

                <div class="padding">
                    <div class="full col-sm-9">

                        <!-- content -->
                        <div class="row">

                            <!-- main col left -->
                            <div class="col-sm-5">

                                <div class="well">
                                    <form action="${pageContext.request.contextPath}/PostServlet" method="POST" class="form-horizontal" role="form">
                                        <h4>What is on your mind?</h4>
                                        <div class="form-group" style="padding:14px;">
                                            <textarea name="text" class="form-control" placeholder="Update your status"></textarea>
                                        </div>
<%--                                        <button class="btn btn-primary pull-right" type="button">Post</button>--%>
                                        <input type="submit" class="btn btn-primary pull-right" value="Post">
                                        <ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i>
                                        </a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li>
                                            <li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
                                    </form>
                                </div>

                            </div>

                            <!-- main col right -->
                            <div class="col-sm-7">
<!----------------------------------------------------------------------------------------------------------------------------------------->
                                <%
                                    List<Post> posts = PostsDAO.getAllUsersPosts();
                                    int totalPosts = posts.size();

                                    for (int i = totalPosts-1; i >= 0; i--) {
                                        int id = posts.get(i).getPostID();

                                        List<Comment> commentList = CommentsDAO.getCommentsUnderPost(id);

                                        session.setAttribute("post", posts.get(i));
                                        User user = UserDetailsDAO.getUserFromDBbyID(posts.get(i).getUserID());
                                        out.println("<div class=\"panel panel-default\">");
                                        out.println("<div class=\"panel-body\">");
                                        out.println("<img src=\"./150x150.gif\" class=\"img-circle pull-right\">");
                                        out.println("<h3><u style=\"color:#3B5999;\">");
                                        out.println(user.getfName());
                                        out.println(" ");
                                        out.println(user.getlName());
                                        out.println("</u></h3>");
//                                        <a href=\"#\">Keyword: Bootstrap</a>");
                                        out.println("<div class=\"clearfix\"></div>");
//                                        out.println("<hr>");
                                        out.println("<div>");
                                        out.println("<p style=\"font-size: 18px;\">");
                                        out.println(posts.get(i).getMessage());
                                        out.println("</p>");
                                //  Posts likes start
                                        out.println("<br><br><span><u style=\"color:#3B5999; \">Likes:</u>" + " " + posts.get(i).getLikes() + "</span>");
                                        out.println("<form action=\"/LikeServlet\">");
                                        out.println("<div style=\"display:flex; justify-content:flex-end;\">");
                                        out.println("<input type=\"hidden\" name=\"likeType\" value=\"postsLike\"");

                                        //Holds the ID of the post's creator
                                        out.println("<span>");
                                        out.print("<input type=\"hidden\" name=\"userID\" value=\"");
                                        out.print(posts.get(i).getUserID());
                                        out.print("\" />");
                                        out.println("</span>");

                                        //Holds the ID of the post itself
                                        out.println("<span>");
                                        out.print("<input type=\"hidden\" name=\"postID\" value=\"");
                                        out.print(posts.get(i).getPostID());
                                        out.print("\" />");
                                        out.println("</span>");

                                        out.println("<input type=\"submit\" class=\"btn btn-primary pull-right\" id=\"edit\" value=\"Edit\" formaction=\"/EditServlet\" />&nbsp");
                                        out.println("<input type=\"submit\" class=\"btn btn-primary pull-right\" id=\"delete\" value=\"Delete\" formaction=\"/DeleteServlet\" />&nbsp");
                                        out.println("<input type=\"submit\" class=\"btn btn-primary pull-right\" id=\"like\" value=\"Like\" onClick=\"HideLikeShowUnlike()\"/>&nbsp");
                                        out.println("<input type=\"submit\" class=\"btn btn-primary pull-right\" id=\"unlike\" value=\"Unlike\" formaction=\"/UnlikeServlet\" onClick=\"HideUnlikeShowLike()\"/>");
                                        out.println("</div>");
                                        out.println("</form>");
                                        out.println("</div>");
                                        out.println("<hr>");
                                //  Posts likes end
                                //  Posts unlikes start
//                                        out.println("<form action=\"/UnlikeServlet\">");
//                                        out.print("<input type=\"hidden\" name=\"postID\" value=\"");
//                                        out.print(posts.get(i).getPostID());
//                                        out.print("\" />");
//                                        out.println("<input type=\"submit\" class=\"btn btn-primary pull-right\" id=\"unlike\" value=\"Unlike\" onClick=\"HideUnlikeShowLike()\"/>");
//                                        out.println("</form>");
//                                        out.println("</div>");
//                                        out.println("<hr>");
                                //  Posts unlikes end

                                //For the comments
                                        for (int j = 0; j < commentList.size(); j++) {
                                            User commenter = CommentsDAO.getCommenter(commentList.get(j).getCommentID(), commentList.get(j).getPostID());
                                            out.println("<div>");
                                            out.print(">> ");
                                            out.print("<u style=\"color:#3B5999;\">" + commenter.getfName() + " " + commenter.getlName() + "</u>");
                                            out.println(":");
                                            out.print("<i>");
                                            out.println(commentList.get(j).getMessage());
                                        //Comments like start
                                            out.println("<br><br><span><u style=\"color:#3B5999; \">Likes:</u>" + " " + commentList.get(j).getLikes() + "</span>");
                                            out.println("</i>");
                                            out.println("<form action=\"/LikeServlet\">");
                                            out.println("<div style=\"display:flex; justify-content:flex-end;\">");
                                            out.println("<input type=\"hidden\" name=\"likeType\" value=\"commentsLike\"");

                                            //Holds ID of the comment itself
                                            out.println("<span>");
                                            out.println("<input type=\"hidden\" name=\"commentID\" value=");
                                            out.println(commentList.get(j).getCommentID());
                                            out.println(" />");
                                            out.println("</span>");

                                            //Holds ID of the user who made the comment
                                            out.println("<span>");
                                            out.println("<input type=\"hidden\" name=\"userID\" value=");
                                            out.println(commentList.get(j).getUserID());
                                            out.println(" />");
                                            out.println("</span>");

                                            out.println("<input type=\"submit\" class=\"btn btn-primary pull-right\" value=\"Like\" />");
                                            out.println("</div>");
                                            out.println("</form>");
                                        //Comments like end
                                            out.println("</div>");
                                            out.println("<hr>");
                                        }

                                        out.println("<form action=\"/CommentServlet\" method=\"POST\">");
                                        out.println("<input type=\"hidden\" name=\"postID\" value=");
                                        out.println(id);
                                        out.println(" />");
                                        out.println("<div class=\"input-group\">");
                                        out.println("<div class=\"input-group-btn\">");
                                        out.println("<button class=\"btn btn-default\">+1</button><button class=\"btn btn-default\"><i class=\"glyphicon glyphicon-share\"></i></button>");
                                        out.println("</div>");
                                        out.println("<input class=\"form-control\" name=\"comment\" placeholder=\"Add a comment..\" type=\"text\">");
                                        out.println("</div>");
                                        out.println("</form>");
                                        out.println("</div>");
                                        out.println("</div>");
                                    }
                                %>

<!----------------------------------------------------------------------------------------------------------------------------------------->



                            </div>
                        </div><!--/row-->

                    </div><!-- /col-9 -->
                </div><!-- /padding -->
            </div>
            <!-- /main -->

        </div>
    </div>
</div>


<!--post modal-->
<div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                Update Status
            </div>
            <div class="modal-body">
                <form class="form center-block">
                    <div class="form-group">
                        <textarea class="form-control input-lg" autofocus="" placeholder="What do you want to share?"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div>
                    <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
                    <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script type="text/javascript" src="assets/js/jquery.js"></script>--%>
<%--<script type="text/javascript" src="assets/js/bootstrap.js"></script>--%>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('[data-toggle=offcanvas]').click(function() {
            $(this).toggleClass('visible-xs text-center');
            $(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
            $('.row-offcanvas').toggleClass('active');
            $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
            $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
            $('#btnShow').toggle();
        });
    });

    //Controls the like and unlike buttons
    // var x = document.getElementById("like");
    // var y = document.getElementById("unlike");
    //
    //
    // function HideLikeShowUnlike() {
    //     x.style.display = "none";
    //     y.style.display = "block";
    // }
    //
    // function HideUnlikeShowLike() {
    //     x.style.display = "block";
    //     y.style.display = "none";
    // }
</script>
</body></html>
