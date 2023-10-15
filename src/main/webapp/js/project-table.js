$(document).ready(function() {
	
	$(".btn-xoa").click(function() {

		var id = $(this).attr('id-project')
		var This = $(this)
		
		
		$.ajax({
			method: "DELETE",
			url: `http://localhost:8080/crm_project_02/api/project/delete?id=${id}` , // string literals
		})
		
			.done(function(result) {
				if(result.data == true){
					
					This.closest('tr').remove()
				}
				
				console.log(result.data)
				
			});
			
	})
	
})