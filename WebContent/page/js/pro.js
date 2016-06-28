		$('.imgtable tbody tr:odd').addClass('odd');

		$(function(){	
			//导航切换
			$(".imglist li").click(function(){
				$(".imglist li.selected").removeClass("selected")
				$(this).addClass("selected");
			})	
		})	
		
		$(document).ready(function(){
		  $(".click").click(function(){
		  $(".tip").fadeIn(200);
		  });
		  
		  $(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
		});
		
		  $(".sure").click(function(){
		  $(".tip").fadeOut(100);
		});
		
		  $(".cancel").click(function(){
		  $(".tip").fadeOut(100);
		});
		
		});
		
		function deleteInfo(){
			 if(confirm('确定删除吗?')) {
				 $("#multiDel").submit();
				 return true;
			 }
		}
		
		function delete_valid() {
			var flag = 0;
			$("input[name='Ids']").each(function(){
				if($(this).attr("checked")){
					flag ++;
				}
			});
			if (flag > 0) {
				if(confirm("确定删除这" + flag + "项吗?")) {
					$("#multiDel").submit();
					return true;
				 } 
			} else {
				 alert("你还未选中任何选项");
				 return false;
			}
		}
		
		function commitAddInfo(){
			 if(confirm('确定添加吗?')) {
				 $("#addInfo").submit();
				 return true;
			 }
		}
		
		function commitUpdateInfo(){
			 if(confirm('确定修改吗?')) {
				 $("#updateInfo").submit();
				 return true;
			 }
		}
