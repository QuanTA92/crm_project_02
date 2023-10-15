var xhr = new XMLHttpRequest();
xhr.open("PUT", "http://localhost:8080/crm_project_02/user-update", true);
xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

// Thay đổi các giá trị của tham số tương ứng
var params = "firstname=999&lastname=999&fullname=999&username=99&email=99%40gmail.com&password=123456&phone=01234567899&role=1&id=9";

xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        // Xử lý phản hồi từ máy chủ (nếu cần)
        console.log(xhr.responseText);
    }
};

xhr.send(params);
