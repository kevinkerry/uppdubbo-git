
		function checkPayerAcctNbr(){
			 var PayerAcctNbr = $('#PayerAcctNbr').val();
			 var PayerAcctNbrData19 = /^[5-6][0-9]{18}$/;
			 var PayerAcctNbrData16 = /^[5-6][0-9]{13,15}$/;
			 if(PayerAcctNbrData16.test(PayerAcctNbr)||PayerAcctNbrData19.test(PayerAcctNbr)){
				 $('#PayerAcctNbrInfo').text("*");
				 return true;
			 }
			 else if(PayerAcctNbr==""){
				 $('#PayerAcctNbrInfo').text("  卡号输入为空");
				 return false;
			 }else{
				 $('#PayerAcctNbrInfo').text("  卡号输入错误");
				 return false;
			 }
			
			}
		function checkPhone(){
			 var phone = $('#PayerPhoneNo').val();
			 var reg = /^(13|14|15|17|18)[0-9]{9}$/;
			 if(phone==""){
				 $('#PayerPhoneNoInfo').text("  手机号输入为空");
				 return false;
			 }else if(!reg.test(phone)){
				 $('#PayerPhoneNoInfo').text("  手机号输入错误");
				 return false;
			 }else{
				 $('#PayerPhoneNoInfo').text("*");
				 return true;
			 }	
		}
		
		function beforeSubmitFreezeInput(){
               var flag=false;
               flag=checkNll();
               return flag;
			
		}

	
		function beforeSubmit(){
              var flag="false";
              flag=checkPayerAcctNbr()&&checkPhone();
              return flag;

		}
		
		function reloadTokenImg() {
		    document.getElementById('_tokenImg').src="GenTokenImg.do"+"?random="+Math.random();
		}

		function LoadJs() {
		    reloadTokenImg();
		}


		function Cancel(){
			   window.history.back(-1);
	
			}
		
		
		
		
		
		
		
		
		
		