let boardInit = {
	version: 1,
	init: function() {
		$("#board--save").bind("click", () => {
			this.save();
		});
		$("#board--update").bind("click", () => {
			this.update();
		});
		$("#board--delete").bind("click", () => {
			this.delete();
		});

	},
	save: function() {

		// save 함수 실행시 데이터 DOM 접근 해서 데이터 가져 오기
		let sendData = {
			title: $("#title").val(),
			content: $("#content").val()
		};
	
		$.ajax({
			type: "POST",
			url: "/api/board/save",
			data: JSON.stringify(sendData),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if (data.code == 1) {
				alert(data.message);
				location.href = "/";
			}
		}).fail(function(error) {
			alert(error.responseText);
		});
	},
	update: function() {
		
		let boardId = $("#board--id").val();
		let userId = $("#user--id").val(); 
		
		let sendData = {
			id : boardId,
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/update/" + boardId + "/userid/" + userId ,
			data: JSON.stringify(sendData),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			if (data.code == 1) {
				alert(data.message);
				location.href = "/";
			}
		}).fail(function(error) {
			alert(error.responseText);
		});
	},
};

boardInit.init();
