/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BeanClasses.Comment;
import BeanClasses.User;
import BeanClasses.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/MainServlet/*"})
public class MainServlet extends HttpServlet {
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
        HttpSession session = request.getSession();
        ServletContext context;
        context = getServletContext();
        //response.getWriter().print(request.getRequestURI());
        String[] uriParts = request.getRequestURI().split("/");
        String requestURL = uriParts[uriParts.length-1];
        
        if(requestURL.equalsIgnoreCase("login")) {
            String email = request.getParameter("loginEmail");
            String password = request.getParameter("loginPassword");
            User user = UserDB.userExists(email,password);
            System.out.println("user is " + user);
            if(user==null){
                session.setAttribute("user", "");
                session.setAttribute("email", "");
                session.setAttribute("message","Invalid Email/Password or User doesn't exist");
                response.sendRedirect("../Home.jsp");
            }else{
                if(user.getRole().equals("admin")){
                    if(user.getStatus().equals("pending")){
                        session.setAttribute("user", "");
                        session.setAttribute("email", "");
                        session.setAttribute("message","You are not yet approved. Please Contact admin.");
                        response.sendRedirect("../Home.jsp");
                    }else if(user.getStatus().equals("Decline")){
                        session.setAttribute("user", "");
                        session.setAttribute("email", "");
                        session.setAttribute("message","You are Declined. Please Contact admin.");
                        response.sendRedirect("../Home.jsp");
                    }else{
                        session.setAttribute("user", user.getFirstname()+" "+user.getLastname());
                        session.setAttribute("email", email);
                        session.setAttribute("message","");
                        ArrayList<User> pendingUsers = UserDB.getPendingUsers();
                        session.setAttribute("pendingUsers", pendingUsers);
                        response.sendRedirect("../AdminWelcome.jsp");
                    }
                }else{
                	System.out.println(user.getFirstname()+" "+user.getLastname());
                    session.setAttribute("user", user.getFirstname()+" "+user.getLastname());
                    session.setAttribute("email", email);
                    session.setAttribute("message","");
                    ArrayList<Question> questions = QuestionDB.getAllQuestions();
                    session.setAttribute("questions", questions);
                    response.sendRedirect("../Welcome.jsp");
                }
            }
        }else if(requestURL.equalsIgnoreCase("logout")){
            session.setAttribute("user", "");
            session.setAttribute("email", "");
            session.setAttribute("message","");
            response.sendRedirect("../Home.jsp");
        }else if (requestURL.equalsIgnoreCase("register")) {
            String fname = request.getParameter("signupFName");
            String lname = request.getParameter("signupLName");
            String phone = request.getParameter("signupPhone");
            String email = request.getParameter("signupEmail");
            String password = request.getParameter("signupPassword");
            String role = request.getParameter("usertype");
            String status = "";
            if(role != null && role.equals("user")){
                status = "approved";
            }else if (role != null && role.equals("admin")){
                status = "pending";
            }
            User user = new User(0,fname,lname,email,phone,password,status,role);
            UserDB.insert(user);
            
            requestURL="../Home.jsp";
            response.sendRedirect(requestURL);
            //response.getWriter().print(user.toString());
        }else if (requestURL.equalsIgnoreCase("AllQuestions")){
            ArrayList<Question> questions = QuestionDB.getAllQuestions();
            session.setAttribute("questions", questions);
            response.sendRedirect("../Welcome.jsp");
        }else if(requestURL.equalsIgnoreCase("YourQuestions")){
            ArrayList<Question> questions = QuestionDB.getAllQuestionsByUser((String)session.getAttribute("email"));
            session.setAttribute("myquestions", questions);
            response.sendRedirect("../YourQuestions.jsp");
        }else if(requestURL.equalsIgnoreCase("PostQuestion")){
            String title = request.getParameter("qstTitle");
            String desc = request.getParameter("qDesc");
            System.out.println("Description: "+desc);
            String emailid = (String) session.getAttribute("email");
            String status = "N";
            Date postedDate = new Date();
            Question question = new Question(0,title,desc,emailid,status,postedDate);
            QuestionDB.insert(question);
            response.sendRedirect("../AddNewQuestion.jsp");
        }else if(requestURL.equalsIgnoreCase("ApproveAdmin")){
            int id = Integer.parseInt(request.getParameter("userId"));
            int rows = UserDB.ApproveAdmin(id);
            ArrayList<User> pendingUsers = UserDB.getPendingUsers();
            session.setAttribute("pendingUsers", pendingUsers);
            response.sendRedirect("../AdminWelcome.jsp");
        }else if(requestURL.equalsIgnoreCase("DeclineAdmin")){
            int id = Integer.parseInt(request.getParameter("userId"));
            int rows = UserDB.DeclineAdmin(id);
            ArrayList<User> pendingUsers = UserDB.getPendingUsers();
            session.setAttribute("pendingUsers", pendingUsers);
            response.sendRedirect("../AdminWelcome.jsp");
        }else if(requestURL.equalsIgnoreCase("PendingUsers")){
            ArrayList<User> pendingUsers = UserDB.getPendingUsers();
            session.setAttribute("pendingUsers", pendingUsers);
            response.sendRedirect("../AdminWelcome.jsp");
        }else if(requestURL.equalsIgnoreCase("DeclinedUsers")){
            ArrayList<User> declinedUsers = UserDB.getDeclinedUsers();
            session.setAttribute("declinedUsers", declinedUsers);
            response.sendRedirect("../DeclinedUsers.jsp");
        }else if(requestURL.equalsIgnoreCase("ApproveAdmin1")){
            int id = Integer.parseInt(request.getParameter("userId"));
            int rows = UserDB.ApproveAdmin(id);
            ArrayList<User> declinedUsers = UserDB.getDeclinedUsers();
            session.setAttribute("declinedUsers", declinedUsers);
            response.sendRedirect("../DeclinedUsers.jsp");
        }else if(requestURL.equalsIgnoreCase("ContactUS")){
            String emailName = request.getParameter("nameEmail");
            String emailFrom = request.getParameter("fromEmail");
            String subject = request.getParameter("subjectEmail");
            String messageText = request.getParameter("messageEmail");
            Email email = new Email();
            email.sendEMail(emailName,emailFrom,subject,messageText);
            response.sendRedirect("../Home.jsp");
        }else if(requestURL.equalsIgnoreCase("ReportedQuestions")){
            ArrayList<Question> reportedQuestions = QuestionDB.getAllReportedQuestions();
            session.setAttribute("reportedQuestions", reportedQuestions);
            response.sendRedirect("../ReportedQuestions.jsp");
        }else if(requestURL.equalsIgnoreCase("GetQuestion")){
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Question> questions= (ArrayList<Question>) session.getAttribute("questions");
            Question selectedQuestion = null;
            for(Question question : questions){
                if(question.getId() == id){
                    selectedQuestion = question;
                    break;
                }
            }
            session.setAttribute("question", selectedQuestion);
            ArrayList<Comment> listOfComments = QuestionDB.getCommentsForQuestion(selectedQuestion.getId());
            if(listOfComments!=null){
                session.setAttribute("comments", listOfComments);
                System.out.println("Size: "+listOfComments.size());
            }else{
                session.setAttribute("comments", new ArrayList<Comment>());
            }
            response.sendRedirect("../Question.jsp");
        }else if(requestURL.equalsIgnoreCase("PostComment")){
            Question question = (Question) session.getAttribute("question");
            String emailid = (String) session.getAttribute("email");
            String userName = (String) session.getAttribute("user");
            String text = request.getParameter("commentText");
            QuestionDB.postCommnet(new Comment(0,question.getId(),text,userName, emailid));
            ArrayList<Comment> listOfComments = QuestionDB.getCommentsForQuestion(question.getId());
            System.out.println("Size : "+listOfComments.size());
            session.setAttribute("comments", listOfComments);
            response.sendRedirect("../Question.jsp");
        }else if(requestURL.equalsIgnoreCase("ReportQuestion")){
            int id = Integer.parseInt(request.getParameter("questionId"));
            QuestionDB.reportQuestion(id);
            response.sendRedirect("../Question.jsp");
        }else if(requestURL.equalsIgnoreCase("RevokeReport")){
            int id = Integer.parseInt(request.getParameter("questionId"));
            QuestionDB.revokeQuestion(id);
            ArrayList<Question> reportedQuestions = QuestionDB.getAllReportedQuestions();
            session.setAttribute("reportedQuestions", reportedQuestions);
            response.sendRedirect("../ReportedQuestions.jsp");
        }else if(requestURL.equalsIgnoreCase("DeleteQuestion")){
            int id = Integer.parseInt(request.getParameter("questionId"));
            QuestionDB.deleteQuestion(id);
            ArrayList<Question> reportedQuestions = QuestionDB.getAllReportedQuestions();
            session.setAttribute("reportedQuestions", reportedQuestions);
            response.sendRedirect("../ReportedQuestions.jsp");
        }
        else if(requestURL.equalsIgnoreCase("DeleteMyQuestion")){
            int id = Integer.parseInt(request.getParameter("questionId"));
            QuestionDB.deleteQuestion(id);
            ArrayList<Question> questions = QuestionDB.getAllQuestionsByUser((String)session.getAttribute("email"));
            session.setAttribute("myquestions", questions);
            response.sendRedirect("../YourQuestions.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

}
