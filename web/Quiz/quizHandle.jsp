<%-- 
    Document   : quizHandle
    Created on : Jun 6, 2022, 2:25:53 PM
    Author     : Minh Lee
--%>

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
        <title>Quiz Handle</title>

        <script src="https://kit.fontawesome.com/f28f2af9ab.js" crossorigin="anonymous"></script>


    </head>
    <body>
        <div class="container1"> 
            <i class="fa-solid fa-location-dot fa-2x"></i>
            <p id="page" style="font-size: x-large; letter-spacing: 5px; margin-left: 5px; margin-right: 20px;" >1/20</p>
            
            <div class="time">
                <i class="fa-solid fa-hourglass-empty fa-2x"></i>
                <span id="time" style="font-size: x-large;"></span>
            </div>
            
            <%
                List<Question> lq = (List<Question>) request.getAttribute("question"); 
               List<Answer> la = (List<Answer>) request.getAttribute("answer");
            %>

            <script type="text/javascript">
                function setCookie(name,value,se) {   // seconds
                    var expires = "";
                    if (se) {   // seconds
                        var date = new Date();
                        date.setTime(date.getTime() + (se*1000));
                        expires = "; expires=" + date.toUTCString();
                    }
                    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
                }
                function getCookie(name) {
                    var nameEQ = name + "=";
                    var ca = document.cookie.split(';');
                    for(var i=0;i < ca.length;i++) {
                        var c = ca[i];
                        while (c.charAt(0)==' ') c = c.substring(1,c.length);
                        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
                    }
                    return null;
                }
          
          var intervalID;
                function startTimer(duration, display) {
                    var timer = duration, minutes, seconds;
                    intervalID = setInterval(function () {
                        minutes = parseInt(timer / 60, 10);
                        seconds = parseInt(timer % 60, 10);
                        
                        setCookie('setTimer',timer,0.00000005);
                        minutes = minutes < 10 ? "00:0" + minutes : "00:" + minutes;
                        seconds = seconds < 10 ? "0" + seconds : seconds;

                        display.textContent = minutes + ":" + seconds;
                        -- timer;
                        setCookie('setTimer',timer,5);
                        if (timer < 0) {
                            timer = 0;
                            stop();
                        }           
                    }, 1000);
                }
                
                function stop(){
                    clearInterval(intervalID);
                    alert('Score Exam!');
                    review();
                    document.getElementById('score-exam').setAttribute('type','submit');
                            
                    
                }

                window.onload = function () {
                    var x; 
                    var e = getCookie('setTimer')
                    var c = <%= lq.size() %>
                    
                    if((e == null || e == "") && c == 50){
                        x = 30*60;
                        sessionStorage.duration=x;
                    }
                    else if((e == null || e == "") && c < 50 && c > 20){
                        x = 25*60;
                        sessionStorage.duration=x;
                    }
                    else if((e == null || e == "") && c < 21 && c > 5){
                        x = 10*60;
                        sessionStorage.duration=x;
                    }
                    else if((e == null || e == "") && c < 6){
                        x = 7*60;
                        sessionStorage.duration=x;
                    }
                    else
                        x = getCookie('setTimer');
                    
                    document.getElementById('durationQuiz').value = sessionStorage.getItem('duration');
                    
                    var Minutes =  x,
                            display = document.querySelector('#time');
                    startTimer(Minutes, display);
                };
            </script>
        </div>

        <div class="coating" id="coating" style="display: none" ></div>
        <div class="container2">
            
            <form action="quizhandle" id="my-form" method="post">
            
            <% 
                for (int i = 0; i < lq.size(); i++) {
            %>
            
            <div class="quesId" id="quesId<%=i%>" <% if(i==0){ %>style="visibility: visible"<% } %><% else{ %>style="display: none"<% } %> >
                <span><%= (i+1) %>)</span>
                <span>Question ID: <%= lq.get(i).getId() %> </span>
                <input type="number" name="quesId<%=i%>" value="<%= lq.get(i).getId() %>" style="display: none;">
                <input type="text" name="username" value="${requestScope.username}" style="display: none">
                <input type="number" name="topicId" value="${requestScope.topic}" style="display: none">
                <input type="number" id="durationQuiz" name="duration" value="" style="display: none">
                
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
                        }
                    %>
                
                <div class="choice" id="choiceId<%= i %>">
                    <div class="ansA">
                        <input type="radio" name="ans<%=i%>" value="<%= temp.get(0).getId() %>">&nbsp; A. &nbsp;<p><%= temp.get(0).getContent() %></p>
                    </div>
                    
                    <div class="ansB">
                        <input type="radio" name="ans<%=i%>" value="<%= temp.get(1).getId() %>">&nbsp; B. &nbsp; <p><%= temp.get(1).getContent() %></p>
                    </div>
                    
                    <div class="ansC">
                    <input type="radio" name="ans<%=i%>" value="<%= temp.get(2).getId() %>">&nbsp; C. &nbsp; <p><%= temp.get(2).getContent() %></p>
                    </div>
                    
                    <div class="ancD">
                    <input type="radio" name="ans<%=i%>" value="<%= temp.get(3).getId() %>">&nbsp; D. &nbsp; <p><%= temp.get(3).getContent() %></p>
                    </div>
                </div>
                    
                    </div>
                    
            <%
                    }
            %>
            
            </form>
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
                    document.getElementById('next').style.visibility = 'visible';
                    var x = checkVisible();
                    
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
                    
                    // paginate
                    if (document.getElementById('quesId'+x).style.getPropertyValue('display') != 'none'){
                    document.getElementById('page').innerHTML = (x+1)+'/'+<%= lq.size() %>
                    }
                }
                
                if (checkVisible() == 0){
                    document.getElementById('previous').style.visibility = 'hidden';
                }
                
                function next(){
                    
                    var x = checkVisible();
                    
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
                    
                    // paginate
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
                     <button type="button" id="score-exam" form="my-form" onclick="confirmScore()">SCORE EXAM NOW</button>
                 </div>

                 <div class="ans-review">
                     
                    <% for (int i = 0; i < lq.size(); i++) {   %>
                    <button class="button-46" id="button_ques<%= i %>" onclick="jumpToQues(<%= i %>)"><%= (i+1) %></button>
                     <% } %>
                    
                </div>            
                            
                     
                     <script type="text/javascript">
                         
                            function confirmScore() {
                                var n = 0;
                                let s = <%= lq.size() %>;
                                
                                for (var i = 0; i < <%= lq.size()%>; i++) {
                                    
                                    let x = document.getElementById('button_ques'+i).style.getPropertyValue('background-color');
                                    
                                    if (x == 'lightgreen'){
                                        n++;
                                    }
                                    
                                }
                                
                                if (n < s){
                                    if(confirm(n+' of '+s+' Questions Answered')){
                                        document.getElementById('score-exam').setAttribute('type','submit');
                                    }
                                    else{
                                        document.getElementById('score-exam').setAttribute('type','button');
                                    }    
                                }
                                else{
                                    if (confirm('Score Exam ?')){
                                        document.getElementById('score-exam').setAttribute('type','submit');
                                    }
                                    else{
                                        document.getElementById('score-exam').setAttribute('type','button');
                                    }
                                }
                                
                            }
                            function checkAns(){
                                
                                for (var i = 0; i < <%= lq.size()%>; i++) {
                                    
                                    let x = document.getElementsByName('ans'+i);
                                    
                                    for (var j = 0; j < x.length; j++) {
                                    
                                        if (x[j].checked == true){
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
                                    if (x == 'lightgreen'){
                                        document.getElementById('button_ques'+i).style.setProperty('display','none');
                                    }
                                    
                                    }
                                    
                            }
                            
                            function checkAns1(){   // For Answered button
                                
                                var n = <%= lq.size() %>;
                                for (var i = 0; i < <%= lq.size()%>; i++) {
                                    
                                    let x = document.getElementById('button_ques'+i).style.getPropertyValue('background-color');
                                    document.getElementById('button_ques'+i).style.removeProperty('display');
                                    if (x != 'lightgreen'){
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
