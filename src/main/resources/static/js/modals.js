$('document').ready(function () {
    $('#editCandidateFlag').val() == 'true' ? $('#editCandidateModal').modal() : false;
    // $('#editCandidateBtn').onclick('click', function (event) {
    //     event.preventDefault();
    //     href = $(this).attr('href');
    //     $.get(href, function (candidate, status){
    //         $('#candidateId').val(candidate.id);
    //         $('#titleEdit').val(candidate.title);
    //         $('#descriptionEdit').val(candidate.description);
    //         $('#imageEdit').val(candidate.image);
    //     });
    //     $('#editCandidateModal').modal();
    // });
});