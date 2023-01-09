/**
 */
let boardInit = {
	version: 1,  
	init: function() {
		$("#board--save").bind("click", () => {
			this.save();
		});
	},
	save: function () {
	
		// save 함수 실행시 데이터 DOM 접근 해서 데이터 가져 오기
		let sendData = {
			title: $("#title").val(),
			content: $("#content").val()
		}; 
		
		$.ajax({
			type: "POST", 
			url : "/api/board/save", 
			data : JSON.stringify(sendData), 
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if(data.code == 1) {
				alert("글 등록 완료 되었습니다");
				//location.href = "/";
			}
		}).fail(function(error) {
			console.log(error);
			alert("글 수정에 실패 하였습니다");
		}); 
	}
};

boardInit.init();
