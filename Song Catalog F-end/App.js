
//Send the AJAX call to the server
$.ajax({
    //The URL to process the request
    'url' : 'http://localhost:8081/api/songs',
    'type' : 'GET',
    'success' : function(data) {
        var users=document.getElementById('getSongs');
        //You can use any jQuery/JavaScript here!!!
        for(i=0;i<data.length;i++){
            var div=document.createElement('div');
            div.setAttribute('class','browse-song-wrap col-xs-10 col-sm-4 col-md-5 col-lg-4');
            var a=document.createElement('a');
            var figure=document.createElement('figure');
            var img=document.createElement('img');
            img.setAttribute('class','img-songs');
            img.setAttribute('src','https://www.eurovisionary.com/wp-content/uploads/2011/03/Dino_Merlin_c_BHRT.jpg');
            figure.appendChild(img);
            var figCaption = document.createElement('figcaption');
            var name = document.createElement('h4');
            name.innerHTML=data[i].artist.name;
            var genre=document.createElement('h4');
            genre.innerText=data[i].genre;
            figCaption.appendChild(name);
            figCaption.appendChild(genre);
            figure.appendChild(figCaption);
            a.appendChild(figure);
            div.appendChild(a);
            var div2=document.createElement('div');
            div2.setAttribute("class",'browse-song-down');
            var a2=document.createElement('a');
            a2.innerHTML=data[i].songName;
            div2.appendChild(a2);
            div.appendChild(div2);
            /*
            var a=document.createElement('a');
            a.innerText="Profile";
            a.setAttribute('href','Profile.html');
            profile.appendChild(a);
            tr.appendChild(profile);*/
            users.appendChild(div);
        }
    },
    'error': function (xhr, ajaxOptions, thrownError) {

        alert("Status: Failled \n Could not load from the database");
    }

});


$(document).ready(function () {

    reloadPaggination();
    function reloadPaggination() {


    $.get("http://localhost:60276/api/users/count",function (data) {
        var pages= data/5;
        var container=document.getElementById('getPages');
        while(container.childNodes.length > 1){
            container.removeChild(container.lastChild);
        }
        for(i=0;i<pages;i++){

            var li=document.createElement("li");
            var a=document.createElement('a');
            a.setAttribute("id","id"+(i+1));
            a.innerText=i+1;
            li.style.cursor="pointer";
            li.appendChild(a);
            container.appendChild(li);
            $(li).on('click',{key:(i+1)},getUsers);

        }

    });

    }

// for sorting By name and Email in same input
    $("#search").keyup(function () {


// if u want to search only inside just ur page
        var s= (document.getElementById('search').value).toUpperCase();
        var row=document.querySelectorAll('.SearchRow');
        for(i=0;i<row.length;i++){

            var nameColumn =row[i].firstElementChild;
            var emailCoulmn=row[i].children[2].innerHTML;

            if(row[i].firstElementChild||row[i].children[2]){
                if(row[i].firstElementChild.innerHTML.toUpperCase().indexOf(s)>-1||row[i].children[2].innerHTML.toUpperCase().indexOf(s)>-1){
                    row[i].style.display="";
                }
                else{
                    row[i].style.display="none";
                }
            }
        }


    });

   // id1.click({key:id1.text()},storeId);
    $("#id1").click(storeId);
});

function getUsers(n) {
    var num=n.data.key;
    $.get("http://localhost:60276/api/page/"+num,function (data) {
        var users=document.getElementById('getUsers');
        while(users.childNodes.length > 1){
            users.removeChild(users.lastChild);
        }

        for(i=0;i<data.length;i++){
            var tr=document.createElement('tr');
            tr.setAttribute('class','SearchRow');
            var name=document.createElement('td');
            name.innerText=data[i].name;
            name.setAttribute("class",'searchText');
            tr.appendChild(name);
            var age=document.createElement('td');
            age.innerText=data[i].age;
            tr.appendChild(age);
            var email=document.createElement('td');
            email.innerText=data[i].email;
            tr.appendChild(email);
            var blogs=document.createElement('td');
            blogs.innerText=data[i].blogs.length;
            tr.appendChild(blogs);
            var profile=document.createElement('td');
            $(profile).on('click',{key:data[i].userId},storeId);
            var a=document.createElement('a');
            a.innerText="Profile";
            a.setAttribute('href','Profile.html');
            profile.setAttribute('id','id'+i+"'");
            profile.appendChild(a);
            tr.appendChild(profile);
            users.appendChild(tr);
        }
    });
}

function storeId(n) {
    var num =n.data.key;
    localStorage.setItem("idOnLocal", num);
}




