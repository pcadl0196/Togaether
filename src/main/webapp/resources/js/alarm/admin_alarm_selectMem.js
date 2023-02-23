$(document).ready(function() {
	fn_selectBoardList(1);
	
	//검색 감지
	//$("#subKeyword").on("input", function(){fn_selectBoardList(1);});
});

//리스트 세팅 함수
function fn_selectBoardList(pageNo) {
	let comAjax = new ComAjax("common");

	comAjax.setUrl("/admin/alarm/selectMember");
	comAjax.setCallback("fn_selectBoardListCallback");

	//페이징 세팅
	if(!isNull(pageNo)){//함수 호출 시 매개변수를 주었다면
		comAjax.addParam("PAGE_INDEX", pageNo);
	}else if(!isNull($('#PAGE_INDEX').val())){//매개변수가 없지만 페이징 값을 저장하는 input에 값이 있다면
		comAjax.addParam("PAGE_INDEX", $('#PAGE_INDEX').val());
	}else{ comAjax.addParam("PAGE_INDEX", 1); }//그 외: 모두 1페이지
	comAjax.addParam("PAGE_ROW", 10);
	
	//검색 값 추가
	//if(!isNull($('#subKeyword').val())) comAjax.addParam("subKeyword", $('#subKeyword').val());
	
	comAjax.ajax();
}

//ajax 통신 후 세팅 함수
function fn_selectBoardListCallback(data) {
	let total = data.TOTAL;
	let mem_list_body = $("#members_list");
console.log(data);
	//페이징 세팅
	let params = {
		divId : "paging",
		pageIndex : "PAGE_INDEX",
		totalCount : total,
		eventName : "fn_selectBoardList",
		recordCount : 10,
	};
	gfn_renderPaging_B(params);
	
	//시설 리스트 세팅 로직
	if (total == 0) {
		mem_list_body.html("<p class='empty' colspan='5'>조회된 결과가 없습니다.</p>");
	} else {
		let str = "";
		/*$.each(data.list, function(key, value) {
			str += "<input type='checkbox' name='target' value='"+value.MEM_ID+"' id='"+value.MEM_ID+"'>"
					+"<label for='"+value.MEM_ID+"'>"+value.MEM_DOG_NAME+"</label>";
		});*/
		mem_list_body.html(str);
	}
}