
    function change_print_type(select_value) {
        var print_type = document.getElementById("type");
        var index = print_type.selectedIndex;
        //var select_value = print_type.options[index].value();

        var location_row = document.getElementById("location");
        var code_row = document.getElementById("code");
        var get_code = document.getElementById("get_code");
        var input_code = document.getElementById("input_code");

        if (select_value.indexOf("order") !== -1) {
            if (location_row != null) {
                //if (location_row.style.display === (document.all ? "block" : "table-row")) {
                //    location_row.style.display = "none";
                //}
                //else {
                location_row.style.display = "";
                //}
            }
            else{
                alert("location_row is null!")
            }
            if (code_row != null) {
                //if (location_row.style.display === (document.all ? "block" : "table-row")) {
                code_row.style.display = "none";
                //}
                //else {
                //    code_row.style.display = (document.all ? "block" : "table-row");
                //}
            }
            else{
                alert("code_row is null!")
            }
            if(get_code != null){
                get_code.style.display = "none";
            }
            else{
                alert("get_code is null!")
            }
        }
        else if (select_value.indexOf("immediate") !== -1) {
            if (location_row != null) {
                //if (location_row.style.display === (document.all ? "block" : "table-row")) {
                location_row.style.display = "none";
                //}
                //else {
                //    location_row.style.display = (document.all ? "block" : "table-row");
                //}
            }
            else{
                alert("location_row is null!")
            }
            if (code_row != null) {
                //if (location_row.style.display === (document.all ? "block" : "table-row")) {
                //    code_row.style.display = "none";
                //}
                //else {
                code_row.style.display = "";
                //}
            }
            else{
                alert("code_row is null!")
            }
            if(get_code != null){
                get_code.style.display = "";
            }
            else{
                alert("get_code is null!")
            }
        }
    }
