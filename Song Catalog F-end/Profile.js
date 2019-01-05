
//geting from the App page user id value
var num =localStorage.getItem("idOnLocal");
//get the user info and print them
$.get("http://localhost:60276/api/user/"+num,function (d) {
    var users=document.getElementById('getUser');
    $(".name").text(d[0].name);
    $(".email").text(d[0].email);
    $("#age").text(d[0].age);

}).fail(function () {
    alert("Failed to get User Information from the database");
});

$(document).ready(function () {

    jQuery.datetimepicker.setLocale('en');
    //get the blogs and print them
    getBlogs();
    function getBlogs() {
        $.get("http://localhost:60276/api/blogs/"+num,function (data) {
            var users=document.getElementById('getUser');
            while(users.childNodes.length > 1){
                users.removeChild(users.lastChild);
            }
            for(i=0;i<data.length;i++){
                var tr=document.createElement('tr');
                tr.setAttribute('class','SearchRow');
                var title=document.createElement('td');
                title.innerText=data[i].title;
                title.setAttribute("class",'searchText');
                tr.appendChild(title);
                var summary=document.createElement('td');
                summary.innerText=data[i].summary;

                tr.appendChild(summary);
                var publishDate=document.createElement('td');
                var date=data[i].publishDate;
                var myDate=moment(date).format("hh:mm,DD-MM-YYYY");

                publishDate.innerText=myDate;
                tr.appendChild(publishDate);
                var update=document.createElement('td');
                var a=document.createElement('a');
                a.setAttribute("href","#");
                a.innerText="Update";
                a.setAttribute('data-toggle',"modal");
                a.setAttribute('data-target',"#updateModal");

                var blogIDD=data[i].blogId;
                $(update).on('click',{key:blogIDD},sendPut);
                update.appendChild(a);
                tr.appendChild(update);
                users.appendChild(tr);

            }
        }).fail(function () {
            alert("Failed to Users Blogs from the database");
        });
    }



//data picker for moy update model
    $("#updateDate").datetimepicker({
        format:'H:m d-m-Y',
        startDate:currentDate
    });

    // search for blog title
    $("#search").keyup(function () {
        var s = (document.getElementById('search').value).toUpperCase();
        var row = document.querySelectorAll('.SearchRow');
        for (i = 0; i < row.length; i++) {

            var nameColumn = row[i].firstElementChild;
            var emailCoulmn = row[i].children[2].innerHTML;

            if (row[i].firstElementChild) {
                if (row[i].firstElementChild.innerHTML.toUpperCase().indexOf(s) > -1) {
                    row[i].style.display = "";
                }
                else {
                    row[i].style.display = "none";
                }
            }
        }
    });

    //tacking value of current date and store
    var d = new Date();
    var currentDate= moment(d).format("hh:mm, DD-MM-YYYY");
    var startDate=$("#startDate");
        var endDate=$("#endDate");

        startDate.datetimepicker({
            format:'H:m d/m/Y'
        });

        endDate.datetimepicker({
                format:'H:m d-m-Y'
        });

        //check the dates when the user commits a change
startDate.on('change',function () {

    var start = moment(startDate.val(),"hh:mm , DD-MM-YYYY").toDate();
    var end = moment(endDate.val(),"hh:mm , DD-MM-YYYY").toDate();
    console.log(end);
    if(endDate.val()){
        checkSearchDates(start,end);
    }

    }
);
    //check the dates when the user commits a change
    endDate.on('change',function () {

        var start = moment(startDate.val(),"hh:mm , DD-MM-YYYY").toDate();
        var end = moment(endDate.val(),"hh:mm , DD-MM-YYYY").toDate();
        console.log(start);
        if(startDate.val()){
            checkSearchDates(start,end);
        }
    });

    $('#searchDate').on('click',function () {
        var start = moment(startDate.val(),"hh:mm , DD-MM-YYYY").toDate();
        var end = moment(endDate.val(),"hh:mm , DD-MM-YYYY").toDate();
        if(checkSearchDates(start,end)===true){
            //change date to milliseconds
            var miliStart =Date.parse(start);
            var miliEnd= Date.parse(end);

            $.get("http://localhost:60276/api/blogs/"+num+"?startDate="+miliStart+"&endDate="+miliEnd,function (data) {
                var users=document.getElementById('getUser');
                while(users.childNodes.length > 1){
                    users.removeChild(users.lastChild);
                }
                for(i=0;i<data.length;i++){
                    var tr=document.createElement('tr');
                    tr.setAttribute('class','SearchRow');
                    var title=document.createElement('td');
                    title.innerText=data[i].title;
                    title.setAttribute("class",'searchText');
                    tr.appendChild(title);
                    var summary=document.createElement('td');
                    summary.innerText=data[i].summary;
                    tr.appendChild(summary);
                    var publishDate=document.createElement('td');
                    var date=data[i].publishDate;
                    var myDate=moment(date).format("hh:mm,DD-MM-YYYY");

                    publishDate.innerText=myDate;
                    tr.appendChild(publishDate);
                    var update=document.createElement('td');
                    var a=document.createElement('a');
                    a.setAttribute("href","#");
                    a.innerText="Update";
                    a.setAttribute('data-toggle',"modal");
                    a.setAttribute('data-target',"#updateModal");

                    var blogIDD=data[i].blogId;
                    $(update).on('click',{key:blogIDD},sendPut);
                    update.appendChild(a);
                    tr.appendChild(update);
                    users.appendChild(tr);

                }
            }).fail(function () {
                alert("Failed to get the filtered blogs!!");
            });
        }
    });
    function checkSearchDates(start,end) {

        if(moment(start).isBefore(end)===false){
            $('#error').css('display',"block")
            startDate.css('border-color','red');
            endDate.css('border-color','red');
            return false
        }
        else{
            $('#error').css('display',"none")
            endDate.css('border-color', '#ccc');
            startDate.css('border-color', '#ccc');
            //startDate.removeAttr('placeholder');
            return true;
        }
    }
        //
       var getdate= $("#date");
       getdate.datetimepicker({
                   format:'H:m, d-m-Y',
                   minDate:currentDate
       });




    var title=$("#title");
    title.on("input",function () {
        checckTitle(title);

    });


    var summary=$("#summary");
    summary.on("input",function () {
       checkSummary(summary);
    });
    var content=$("#content");

    content.on("input",function () {
        checkContent(content);
    });

    getdate.on("change",function () {
        checckDate(getdate);
    });

    var uTitle=$("#updateTitle");
    uTitle.on("input",function () {
        checckTitle(uTitle);

    });


    var uSummary=$("#updatSummary");
    uSummary.on("input",function () {
        checkSummary(uSummary);
    });

    var uContent=$("#updateContent");

    uContent.on("input",function () {
        checkContent(uContent);
    });
    var uDate=$('#updateDate');

    uDate.on("change",function () {
        checckDate(uDate);
    });


//rgb(204, 204, 204)

    $("#save").on('click',(function () {
        "user strict";

        //creating jsonObject to hold my POST data
        var summary= $("#summary");
        var content=$("#content");
        var title=$("#title");
        var getdate= $("#date");
        checckTitle(title);
        checkSummary(summary);
        checckDate(getdate);
        checkContent(content);
        if(checckTitle(title)===true&&checkSummary(summary)===true
            &&checckDate(getdate)===true&&  checkContent(content)===true){

            var obj = {};
            obj.userId=num;
            obj.blogId=0;
            obj.title= title.val();
            obj.summary= summary.val();
            obj.content= content.val();

            //getting the value from the date picker
            var date =getdate.val();

            // parsing it to datetime type by moment js
            var dt = moment(date,"hh:mm , DD-MM-YYYY").toDate();

            // parsing it to json
            var jsonDate=dt.toJSON();

            //passing it to my json opject
            obj.publishDate=jsonDate;

            //Send a Post request to create my new Blog
            $.post("http://localhost:60276/api/blog",obj,function (data,status) {
                $('#myModal').modal('hide');
                alert("Status: " + status+"\n successfuly created , close to see the change");
                $('#save').attr('data-dismiss','modal');
                getBlogs();
                summary.val("");
                $(summary,title,getdate,content).removeAttr("placeholder");
                title.val("");
                getdate.val("");
                content.val("");


            }).fail(function () {
                alert("Failed to save your data in the database");
            });

        }
        else{
            $('#save').removeAttr('data-dismiss');
        }


    }));

    function getblogForUpdate(id) {
        $.get("http://localhost:60276/api/blog/"+id,function (data) {
            var users=document.getElementById('getUser');
            $("#updatSummary").val(data[0].summary);
            $("#updateTitle").val(data[0].title);
            $("#updateContent").text(data[0].content);
            var date=data[0].publishDate;
            var myDate=moment(date).format("hh:mm,DD-MM-YYYY");
            $("#updateDate").val(myDate);

        }).fail(function () {
            alert("Failed to get data from the database");
        });
    }
    var id=0;
    function sendPut(n) {
         id = n.data.key;
        getblogForUpdate(id);
    }
    $("#update").click(function () {

        var summary= $("#updatSummary");
        var content=$("#updateContent");
        var title=$("#updateTitle");
        var getdat= $("#updateDate");


        //checking if the inputs are correct
        if(checckTitle(title)===true&&checkSummary(summary)===true
            &&checckDate(getdat)===true&&  checkContent(content)===true){
        //creating jsonObject to hold my POST data
        var obj = {};
        obj.userId=num;
        obj.blogId=id;
        obj.title= $("#updateTitle").val();
        obj.summary= $("#updatSummary").val();
        obj.content= $("#updateContent").val();

        //getting the value from the date picker
        var date =$("#updateDate").val();

        // parsing it to datetime type by moment js
        var dt = moment(date,"hh:mm , DD-MM-YYYY").toDate();

        // parsing it to json
        var jsonDate=dt.toJSON();

        //passing it to my json opject
        obj.publishDate=jsonDate;

        //Send a Post request to create my new Blog
        $.ajax({
            //The URL to process the request
            'url' : 'http://localhost:60276/api/blog',
            'type' : 'PUT',
            'data' : obj,
            'success' : function(data,status) {
                alert("Status: " + status+"\n Successfuly Updated !!");
                $('#myModal').modal('hide');
                getBlogs();
            },
            'error':function (xhr, ajaxOptions, thrownError) {

                alert("Status Failed\n"+thrownError);
            }
        });
        }
        else {
        $('#update').removeAttr('data-dismiss');
        }
    });

    //functions for checking the inputs
    function checkContent(content) {
        if(content.val().length>3500||content.val().length<1){
            content.css('border-color','red');
            content.attr("placeholder", "Enter a valid content");
            return false;
        }
        else {
            content.css('border-color','#ccc');
            content.removeAttr('placeholder');
            return true;
        }
    }

    function checkSummary(summary) {
        if(summary.val().length>350||summary.val().length<1) {
            summary.css('border-color', 'red');
            summary.attr("placeholder", "Enter a valid summary");
            return false;
        }
        else {
            summary.css('border-color','#ccc');
            summary.removeAttr('placeholder');
            console.log(summary.css('border-color'));
            return true;
        }
    }
    function checckTitle(title) {
        if (title.val().length > 64 || title.val().length < 1) {
            title.css('border-color', 'red');
            title.attr("placeholder", "Enter a valid title");
            return false;
        }
        else {
            title.css('border-color', '#ccc');
            title.removeAttr('placeholder');
            return true;
        }
    }
    function checckDate(date) {
        if(date.val().length<1) {
            date.css('border-color', 'red');
            date.attr("placeholder", "Insert Date please");
            return false;
        }
        else {
            date.css('border-color','#ccc');
            date.removeAttr('placeholder');
            return true;
        }
    }
});
