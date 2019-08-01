var app = new function () {
    var employees = [];
    var mode = "";
    // var url = "https://zigwheels.herokuapp.com/api/teams/";
    // http://localhost:8080/api/teams/
    var url = "http://localhost:8080/api/employee/";
    // var url="https://employeeab.herokuapp.com/api/employee/";
    this.FetchAll = function () {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {
                employees = JSON.parse(xhttp.responseText);
                app.printtable();
            }
        };
        xhttp.open("GET", url, true);
        xhttp.send();
    };
    this.printtable = function () {
        var html = "<table class='table table-bordered'>";
        for (var i  in employees) {
            html += "<tr>";
            html += "<td>" + employees[i].id + "</td>";
            html += "<td>" + employees[i].name + "</td>";
            html += '<td><button class="glyphicon glyphicon-pencil   btn fa-edit "  onclick="app.Edit(' + employees[i].id + ')"></button></td>';
            html += '<td><button class="glyphicon glyphicon-trash btn fa-delete "  onclick="app.Delete(' + employees[i].id + ')"></button></td>';
            html += "</tr>";
        }
        html += "</table>";
        document.getElementById("Employee").innerHTML = html;
    };

    this.Edit = function (id) {
        mode = "edit";
        document.getElementById('btn').innerHTML = "Update";
        for (var i = 0; i < employees.length; i++) {

            if (employees[i].id == id) {
                // //alert(id);
                document.getElementById('id').value = employees[i].id;
                document.getElementById('name').value = employees[i].name;
            }
        }
    };

    this.saveorupdate = function () {
        var id = document.getElementById('id').value;
        var name = document.getElementById('name').value;
        if (mode == "") {
            //alert("save called");
            // {"id": 0, "name": "TeamIndia"}
            var newTeam = { "id": 0, "name": name };
            //alert(newTeam);
            //  var url = "http://zigwheels.herokuapp.com/api/teams/";
            var xhr = new XMLHttpRequest();
            xhr.onload = function () {
                if (xhr.readyState == 4 && xhr.status == "201") {
                    app.FetchAll();
                }
            }
            xhr.open("POST", url, true);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhr.send(JSON.stringify(newTeam));
            app.FetchAll();
        }
        else {
            //alert("update called");

            // var updateTeam = {};
            // updateTeam.teamid=id;
            // updateTeam.teamname=name;

            var updateTeam = { "id": id, "name": name };
            //alert(updateTeam);

            var xhr = new XMLHttpRequest();
            xhr.onload = function () {
                if (xhr.readyState == 4 && xhr.status == "200") {
                    app.FetchAll();
                }
            }
            xhr.open("PUT", url + updateTeam.id, true);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            //alert(JSON.stringify(updateTeam));
            xhr.send(JSON.stringify(updateTeam));
            document.getElementById('btn').innerHTML = "Add";
            mode = "";
            app.FetchAll();
        }

        document.getElementById('id').value = "";
        document.getElementById('name').value = "";
    };

    this.Delete = function (id) {
        // console.log("delete called " + id);
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", url + id, true);
        xhr.onload = function () {
            if (xhr.readyState == 4 && xhr.status == "204") {
                app.FetchAll();
            }
        }
        xhr.send(null);
        app.FetchAll();
    };
}