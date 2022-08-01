<%-- 
    Document   : quizReview
    Created on : Jun 16, 2022, 11:03:46 AM
    Author     : Minh Lee
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="model.minhlee.Quiz_review"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.LSHL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.minhlee.Answer"%>
<%@page import="model.minhlee.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Quiz/quiz.css"/>
        <link rel="stylesheet" href="Quiz/button.css"/>
        <title>Quiz Review</title>

        <script src="https://kit.fontawesome.com/f28f2af9ab.js" crossorigin="anonymous"></script>


    </head>
    <body>
        <div class="container1"> 
            <i class="fa-solid fa-location-dot fa-2x"></i>
            <p id="page" style="font-size: x-large; letter-spacing: 5px; margin-left: 5px; margin-right: 20px;" >1/20</p>
            
            <div class="time">
                <i class="fa-solid fa-hourglass-empty fa-2x"></i>
                <span id="time" style="font-size: x-large;">00:00:00</span>
            </div>
            
            <a href="question?topic=${topicId}" style="margin-right: 10px;">
                <button>X</button>
            </a>

        </div>

        <div class="coating" id="coating" style="display: none" ></div>
        <div class="container2">
            
            <%! int UserAnswer (int lqId, List<Answer> temp, List<Quiz_review> qz){ // temp will be list answer after check questionid
                    
                        for (int i = 0; i < qz.size(); i++) {
                                if (qz.get(i).getQuestionId() == lqId ){
                                    for (int j = 0; j < temp.size(); j++) {
                                            if (temp.get(j).getId() == qz.get(i).getAnswerId())
                                                return (j+1);
                                        }
                                    
                                }
                            }
                        return -1;
                }
            
                int isSolution (int lqId, List<Answer> temp, List<Quiz_review> qz){
                    
                        for (int i = 0; i < qz.size(); i++) {
                                if (qz.get(i).getQuestionId() == lqId){
                                    for (int j = 0; j < temp.size(); j++) {
                                            if (temp.get(j).getId() == qz.get(i).getIsSolutionId())
                                                return (j+1);
                                        }
                                }
                            }
                        return -1;
                }
                
                String explain (int lqId, List<Answer> temp, List<Quiz_review> qz){
                    
                        for (int i = 0; i < qz.size(); i++) {
                                if (qz.get(i).getQuestionId() == lqId){
                                    for (int j = 0; j < temp.size(); j++) {
                                            if (temp.get(j).getId() == qz.get(i).getIsSolutionId())
                                                return qz.get(i).getExplain();
                                        }
                                }
                            }
                        return null;
                }
            %>
            
            <% List<Question> lq = (List<Question>) request.getAttribute("question"); 
               List<Answer> la = (List<Answer>) request.getAttribute("answer");
               
               
                for (int i = 0; i < lq.size(); i++) {
                        
                    
            %>
            
            
            <div class="quesId" id="quesId<%=i%>" <% if(i==0){ %>style="visibility: visible"<% } %><% else{ %>style="display: none"<% } %> >
                <span><%= (i+1) %>)</span>
                <span>Question ID: <%= lq.get(i).getId() %> </span>
                <input type="number" name="quesId<%=i%>" value="<%= lq.get(i).getId() %>" style="display: none;">
            </div>
            
            <script type="text/javascript">
                if (document.getElementById('quesId<%=i%>').style.getPropertyValue('display') != 'none'){
                    document.getElementById('page').innerHTML = <%=i+1%>+'/'+<%= lq.size() %>
                }
            </script>
            
            <div class="quiz" id="quiz<%=i%>"<% if(i==0){ %>style="visibility: visible"<% } %><% else{ %>style="display: none"<% } %>>
                
                <div class="ques">
                    <p> <%= lq.get(i).getContent() %> </p>
                </div>
                
                    <% 
                        List<Answer> temp = new ArrayList<Answer>();
                        for (int j = 0; j < la.size(); j++) {
                            if (la.get(j).getQuesitonId() == lq.get(i).getId()){
                                temp.add(la.get(j));
                            }
                        }   // take 4 answers
                        
                        List<Quiz_review> qz = (List<Quiz_review>) request.getAttribute("quiz_review");
                        int uans = UserAnswer(lq.get(i).getId(), temp,qz);
                        int isSolution = isSolution(lq.get(i).getId(), temp,qz);
                        String explain = explain(lq.get(i).getId(), temp, qz);
                    %>
                
                    <div class="explain_ans">
                        <button id="button_ex<%=i%>" onclick="visibleEx(<%= i %>)">Explain</button>
                        <p id="explain<%= i %>" style="display: none"> <%= explain %>  </p>
                    <script type="text/javascript">
                        var k = 0;
                        function visibleEx(y){
                            k++;
//                            alert(k);
                            if (k == 1){
                                document.getElementById('explain'+y).style.removeProperty('display');
                            }
                            else if (k == 2){
                                k=0;
                                document.getElementById('explain'+y).style.display = 'none';
                                
                            }
                        }
                    </script>
                    </div>
                    
                
                <div class="choice" id="choiceId<%= i %>">
                    <div class="divAns<%=i%>" <% if (isSolution != 1 && uans == 1){ %> style="background-color: red"<% } %> <% if (isSolution == 1){ %> style="background-color: lightgreen"<% } %>>
                        <input type="radio" name="ans<%=i%>" value="<%= temp.get(0).getId() %>" <% if (uans == 1){ %> checked<% } %>  >&nbsp; A. &nbsp;<p><%= temp.get(0).getContent() %></p>
                    </div>
                    
                    <div class="divAns<%=i%>" <% if (isSolution != 2 && uans == 2){ %> style="background-color: red"<% } %> <% if (isSolution == 2){ %> style="background-color: lightgreen"<% } %>>
                        <input type="radio" name="ans<%=i%>" value="<%= temp.get(1).getId() %>" <% if (uans == 2){ %> checked<% } %>  >&nbsp; B. &nbsp; <p><%= temp.get(1).getContent() %></p>
                    </div>
                    
                    <div class="divAns<%=i%>" <% if (isSolution != 3 && uans == 3){ %> style="background-color: red"<% } %> <% if (isSolution == 3){ %> style="background-color: lightgreen"<% } %>>
                    <input type="radio" name="ans<%=i%>" value="<%= temp.get(2).getId() %>" <% if (uans == 3){ %> checked<% } %>  >&nbsp; C. &nbsp; <p><%= temp.get(2).getContent() %></p>
                    </div>
                    
                    <div class="divAns<%=i%>" <% if (isSolution != 4 && uans == 4){ %> style="background-color: red"<% } %> <% if (isSolution == 4){ %> style="background-color: lightgreen"<% } %> >
                    <input type="radio" name="ans<%=i%>" value="<%= temp.get(3).getId() %>" <% if (uans == 4){ %> checked<% } %> >&nbsp; D. &nbsp; <p><%= temp.get(3).getContent() %></p>
                    </div>
                </div>
                    
                    <script type="text/javascript">
                        var x = document.getElementsByName('ans<%=i%>');
                        
                        for (var i = 0, max = x.length; i < max; i++) {
                            x[i].disabled = true;
                        }
                        
                    </script>
                    
                    </div>
                    
            <%
                    }
            %>
            
            <div class="footer">
                <div>
                <button class="button-39" onclick="review()">Review Progress</button>
                    <script type="text/javascript">
                            function review(){  

                                document.getElementById('coating').style.removeProperty('display');
                                document.getElementById('review_page').style.removeProperty('display');
                                checkAns();
                                
                            }                            
                    </script>
                </div>
                <div>
                    <button class="button-39" id="previous" onclick="previous()">Previous</button>
                    <button class="button-39" id="next" onclick="next()">Next</button>
                </div>
                
            </div>
            
            
            <script type="text/javascript">
                
                function checkVisible(){
                    for (var i = 0, max = <%= lq.size() %>; i < max; i++) {
                        let x = document.getElementById('quesId'+i).style.display;
                        if (x != 'none')
                            return i;  
                    }
                }
                
                function previous(){
                    k = 0; // for div<class="explain_ans">
                    
                    document.getElementById('next').style.visibility = 'visible';
                    var x = checkVisible();
                    
                    if (k == 0){
                            document.getElementById('explain'+x).style.display = 'none';
                            }
                    
                    if (x == 0){
                        document.getElementById('previous').style.visibility = 'hidden';
                    }
                    if (x != 0) {
                        document.getElementById('previous').style.visibility = 'visible';
                    }
                    
                    document.getElementById('quesId'+x).style.removeProperty('visibility');
                    document.getElementById('quiz'+x).style.removeProperty('visibility');
                    document.getElementById('quesId'+x).style.display='none';
                    document.getElementById('quiz'+x).style.display='none';
                    
                    
                    x-=1;
                    
                    if (x < 0){
                        x = 0;
                    }
                    if (x == 0){
                        document.getElementById('previous').style.visibility = 'hidden';
                    }
                    if (x != 0) {
                        document.getElementById('previous').style.visibility = 'visible';
                    }
                    document.getElementById('quesId'+x).style.removeProperty('display');
                    document.getElementById('quiz'+x).style.removeProperty('display');
                    document.getElementById('quesId'+x).style.visibility='visible';
                    document.getElementById('quiz'+x).style.visibility='visible';
                    if (document.getElementById('quesId'+x).style.getPropertyValue('display') != 'none'){
                    document.getElementById('page').innerHTML = (x+1)+'/'+<%= lq.size() %>
                    }
                }
                
                if (checkVisible() == 0){
                    document.getElementById('previous').style.visibility = 'hidden';
                }
                
                function next(){
                    k = 0; // for div<class="explain_ans">
                    var x = checkVisible();
                    
                    if (k == 0){
                            document.getElementById('explain'+x).style.display = 'none';
                            }
                            
                    if (x == 0){
                        document.getElementById('previous').style.visibility = 'hidden';
                    }
                    if (x != 0) {
                        document.getElementById('previous').style.visibility = 'visible';
                    }
                    if (x == <%= lq.size()-2 %>) {
                        document.getElementById('next').style.visibility = 'hidden';
                    }
                    
                    
                    
                    document.getElementById('quesId'+x).style.removeProperty('visibility');
                    document.getElementById('quiz'+x).style.removeProperty('visibility');
                    document.getElementById('quesId'+x).style.display='none';
                    document.getElementById('quiz'+x).style.display='none';
                    
                    
                    x+=1;
                    
                    if (x > <%= (lq.size()-1) %>){
                        x = <%= (lq.size()-1) %>
                    }
                    if (x == 0){
                        document.getElementById('previous').style.visibility = 'hidden';
                    }
                    if (x != 0) {
                        document.getElementById('previous').style.visibility = 'visible';
                    }
                    document.getElementById('quesId'+x).style.removeProperty('display');
                    document.getElementById('quiz'+x).style.removeProperty('display');
                    document.getElementById('quesId'+x).style.visibility='visible';
                    document.getElementById('quiz'+x).style.visibility='visible';
                    if (document.getElementById('quesId'+x).style.getPropertyValue('display') != 'none'){
                    document.getElementById('page').innerHTML = (x+1)+'/'+<%= lq.size() %>
                    }
                    
                }
                
            </script>
        </div>
         <!--end container 2-->
         
         <!--Pop-up screen review-->
         <div class="reviewProgress" id="review_page" style="display: none">
             <div class="c1"></div>
             <div class="main-review">
                 <div class="title">
                     <h1>Review Progress</h1>
                     <button class="button-46" onclick="exitReview()">X</button>
                     
                     <script type="text/javascript">
                         function exitReview(){
                            document.getElementById('coating').style.setProperty('display','none')  ;
                            document.getElementById('review_page').style.setProperty('display', 'none');
                            checkAns2();
                         }
                     </script>
                 </div>

                 <h5>Review before scoring exam</h5>

                 <div class="choice-review">
                     <div>
                         <button onclick="checkAns0()">UNANSWERED</button>
                         <button onclick="checkAns1()">ANSWERED</button>
                         <button onclick="checkAns2()">ALL QUESTIONS</button>
                     </div>
<!--                     <button type="button" id="score-exam" form="my-form" onclick="confirmScore()">SCORE EXAM NOW</button>-->
                 </div>

                 <div class="ans-review">
                     
                    <% for (int i = 0; i < lq.size(); i++) {   %>
                    <button class="button-46" id="button_ques<%= i %>" onclick="jumpToQues(<%= i %>)"><%= (i+1) %></button>
                     <% } %>
                    
                </div>            
                            
                     
                     <script type="text/javascript">
                         
                            
                            function checkAns(){
                                
                                for (var i = 0; i < <%= lq.size()%>; i++) {
                                    
                                    let x = document.getElementsByName('ans'+i);
                                    let divAns = document.getElementsByClassName('divAns'+i);
                                    for (var j = 0; j < x.length; j++) {
                                        
                                            
                                        if (x[j].checked == true){
                                            let boo = false;
                                            for (var di = 0; di < divAns.length; di++) {
                                                if (divAns[di].style.getPropertyValue('background-color') == 'red'){
                                                    document.getElementById('button_ques'+i).style.setProperty('background-color','red');
                                                    boo = false;
                                                    break;
                                                }
                                                else
                                                    boo = true;
                                            }
                                            if (boo == true)
                                                document.getElementById('button_ques'+i).style.setProperty('background-color','lightgreen');
                                            break;
                                        }
                                        
                                    }
                                }
                            }   // checkAns()
                            
                            function checkAns0(){   // For Unanswered button
                                
                                    for (var i = 0; i < <%= lq.size()%>; i++) {
                                    
                                    let x = document.getElementById('button_ques'+i).style.getPropertyValue('background-color');
                                    
                                    document.getElementById('button_ques'+i).style.removeProperty('display');
                                    if (x == 'lightgreen' || x == 'red'){
                                        document.getElementById('button_ques'+i).style.setProperty('display','none');
                                    }
                                    
                                    }
                                    
                            }
                            
                            function checkAns1(){   // For Answered button
                                
                                var n = <%= lq.size() %>;
                                for (var i = 0; i < <%= lq.size()%>; i++) {
                                    
                                    let x = document.getElementById('button_ques'+i).style.getPropertyValue('background-color');
                                    document.getElementById('button_ques'+i).style.removeProperty('display');
                                    if (x != 'lightgreen' && x!= 'red'){
                                        document.getElementById('button_ques'+i).style.setProperty('display','none');
                                        n--;
                                    }
                                }
                                return n;
                            }
                            
                            function checkAns2(){ // For All Quesiton button
                                for (var i = 0; i < <%= lq.size()%>; i++) {
                                    document.getElementById('button_ques'+i).style.removeProperty('display');
                                }
                            }
                            
                            function jumpToQues(q) { // when click each question system will jump to question which is choosed
                                
                                exitReview();
//                                alert(q);
                                for (var i = 0; i < <%= lq.size() %>; i++) {
                                    if (document.getElementById('quesId'+i).style.getPropertyValue('visibility') == 'visible'){
                                        document.getElementById('quesId'+i).style.removeProperty('visibility');
                                        document.getElementById('quiz'+i).style.removeProperty('visibility');
                                        document.getElementById('quesId'+i).style.setProperty('display','none');
                                        document.getElementById('quiz'+i).style.setProperty('display','none');
                                        break;
                                    }
                                }
//                                        
                                        document.getElementById('quesId'+q).style.removeProperty('display');
                                        document.getElementById('quiz'+q).style.removeProperty('display');
                                        document.getElementById('quesId'+q).style.setProperty('visibility','visible');
                                        document.getElementById('quiz'+q).style.setProperty('visibility','visible');
                            }
                     </script>
                 
             </div>
             <div class="c1"></div>
         </div>
    </body>
</html>
