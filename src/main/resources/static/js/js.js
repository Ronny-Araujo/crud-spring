function searchUser() {
    var name = $("#recipient-name").val();
    if (name != null && name.trim() != '') {
        $.ajax({
            method: "GET",
            url: "findbyname",
            data: "name=" + name,
            success: function (response) {
                $("#tableResult > tbody > tr").remove();

                for (var i = 0; i < response.length; i++) {
                    $('#tableResult > tbody').append(
                        '<tr>' +
                        '<td>' + response[i].id + '</td>' +
                        '<td>' + response[i].name + '</td>' +
                        '<td>' + response[i].age + '</td>' +
                        '<td><button type="button" onclick="editUser(' + response[i].id + ')" class="btn btn-warning">Edit</button></td>' +
                        '<td><button type="button" onclick="deleteUser(' + response[i].id + ')" class="btn btn-danger">Delete</button></td>' +
                        '</tr>');
                }
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("error: " + xhr.responseText);
        });
    }
}

function editUser(id) {
    $.ajax({
        method: "GET",
        url: "findbyid",
        data: "id=" + id,
        success: function (response) {
            $("#id").val(response.id);
            $("#name").val(response.name);
            $("#age").val(response.age);

            $("#modalSearchUser").modal('hide');

        }
    }).fail(function (xhr, status, errorThrown) {
        alert("error: " + xhr.responseText);
    });
}

function deleteUser(id) {
    if (confirm("Delete user?")) {
        $.ajax({
            method: "DELETE",
            url: "delete",
            data: "id=" + id,
            success: function (response) {
                alert(response);
                searchUser();
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("error: " + xhr.responseText);
        });
    }
}

function saveUser() {
    var id = $("#id").val();
    var name = $("#name").val();
    var age = $("#age").val();

    if (name == null || name != null && name.trim() == '') {
        $("#name").focus();
        alert("Name required");
        return;
    }

    if (age == null || age != null && age.trim() == '') {
        $("#age").focus();
        alert("Age required");
        return;
    }

    $.ajax({
        method: "POST",
        url: "save",
        data: JSON.stringify({
            id: id,
            name: name,
            age: age
        }),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            $("#id").val(response.id);
            alert("saved");
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("error saving: " + xhr.responseText);
    });

}