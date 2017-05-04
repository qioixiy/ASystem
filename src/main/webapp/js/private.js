$(document).ready(function () {
  console.log();
  if (g_user_type == "manager") {
    ;
  } else if (g_user_type == "teacher") {
    $("#li-course").remove();
    $("#li-teacher").remove();
  } else if (g_user_type == "student") {
    $("#li-course").remove();
    $("#li-teacher").remove();
    $("#li-student").remove();
    $("#li-paper").remove();
    //li-score-viewall
    $("#li-score-create").remove();
    $("#li-score-delete").remove();
    $("#li-score-modify").remove();
    $("#li-score-query").remove();
    $("#li-score-analysis").remove();
    //$("li-score").remove();
  }
});
