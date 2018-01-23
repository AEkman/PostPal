$(document).ready ( function() {
    var getAllNotes = "localhost:8080/api/notes";
    var createNoteUrl ="http://localhost:8080/api/notes";
    var deleteNoteUrl = "http://localhost:8080/api/notes";
    var updateNoteUrl = "http://localhost:8080/api/notes";
    var findNoteBySearch ="http://localhost:8080/api/find/";

    var $notes = $("#notes");

    function loadNotes() {
        // Clear old notes
        $notes.empty();

        $.ajax({
            url: "http://localhost:8080/api/notes"
        }).then(function(data) {
            console.log(data);

            for(i = 0; i < data.length; i++) {
                // console.log("ID: " + data[i].id + " Created at " + data[i].createdAt + " Updated at " + data[i].updatedAt);

                $($notes).append($('<div class="col-md-4">'
                    + '<div class="card note" id="note' + data[i].id + '">'
                    + '<div class="card-header"> ' + data[i].title + ' </div>'
                    + '<div class="card-body">'
                    + '<p class="card-text"> ' + data[i].content + ' </p>'
                    + '</div>'
                    + '</div>'
                    + '</div>'
                ))
            }
        });

    }


    console.log("notes page");

    loadNotes();

    $("#refreshButton").click(function () {
        loadNotes();
    });




});