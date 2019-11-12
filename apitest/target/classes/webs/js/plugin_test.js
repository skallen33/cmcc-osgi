// JavaScript Document
var currentPageInst = null;
//var servletName = "";
var servletName = "apitest/cmcc.cmd";
Page = function() {
	this.debugEnable = false;
	// this.timeIntervalHandler = null;
	this.recentTestTermCfgArr = new Array();
	this.testItemsTemplate = null;
	this.testItems = null;
	this.testItemsArray = new Array(); //待测试项数组
	this.testItemIdxArray = new Array();
	this.currTestItemIdx = -1;
	this.currTestSubItemName = "";
	this.syncFlag = "0";
	this.allTermParam = new Array();
	this.testingFlag = true; /* True is tesing, false is not! */
	this.queryId = 0;
	this.sendIdEnsureFlag = false;
	
	this.tip="正在测试,请稍等..."
	
	//timer Hanler
	this.monitorTimeHandler = null;
	this.queryTimeHandler = null;
	
	this.pluginName = "com.chinatelecom.all.smartgateway.osgitest";
	this.pluginVersion = "1.1.1";
	this.defaultAppUrl = "https://nos.189cube.com";

	this.initHtmlText = function() {
		$("#PLUGIN_TEXTA_ParamTemplate").attr("readonly", "true");
		
		var urlParamStr = window.location.hash;
		console_log("----urlParamStr1----"+urlParamStr);
		if (urlParamStr && urlParamStr != "")
		{
			urlParamStr = urlParamStr.replace("#", ""); 
		// var urlParamStr = "https://192.168.1.1/index.html#token=123&MAC=faehahgallhl&appurl=pluginUrl";
			console_log("----urlParamStr2----"+urlParamStr);
			var paramArray =  urlParamStr.split("&");
			var paramHash = {};
			
			for (var i = 0; i < paramArray.length; i++)
			{
				var tmp = paramArray[i].split("=");
				console_log(tmp[0]+"="+tmp[1]);
				paramHash[tmp[0]] = tmp[1];
			}
						
			if (typeof(paramHash["appurl"]) == "undefined")
			{
				paramHash["appurl"] = this.defaultAppUrl;
			}
			/* for debug */
			{
				$("#PLUGIN_platformToken").val(paramHash["token"]);
				$("#PLUGIN_platformMac").val(paramHash["MAC"]);
				$("#PLUGIN_platformAppUrl").val(paramHash["appurl"]);
				$("#PLUGIN_platformName").val(this.pluginName);
				$("#PLUGIN_platformVer").val(this.pluginVersion);
			}
			servletName = paramHash["appurl"] + "/plugin/post?token=" + paramHash["token"] + "&MAC=" + paramHash["MAC"] + "&PluginName="+this.pluginName+"&Version="+this.pluginVersion+"&decode=1";
			console_log("----ServletName1----"+servletName);
		}
	}

	this.updateAllCfg = function() {
		if (!this.debugEnable) {
			var postData = {};
			postData.CmdType = "GET_ALL_TEST_ITEMS";
			console_log("----servletName----"+servletName);
			cfgHandler.setCfg(servletName, postData, "this.showAllTestItems");
		} else {
			//for static test
			var pluginTestCfg = {
				"return_Parameter":JSON.stringify(
					{
						"CmdType":"GET_ALL_TEST_ITEMS",
						"Status":"0",
						"TestItems":[
							{
								"Name":"USB Dongle测试",
								"SubItems":[
									{
										"Name":"USB Dongle测试"
									},
								],
								"Method":"Java/C"
							},
							{
								"Name":"本地能力接口测试",
								"SubItems":[
									{
										"Name":"测试接口１",
										"InputParam":{"key1":"val1","key2":"val2"},
										"OutputParam":{"key3":"val3","key4":"val4"}

									},
									{
										"Name":"测试接口2",
										"InputParam":{"key5":"val5","key6":"val6"},
										"OutputParam":{"key7":"val7","key8":"val8"}

									},
									{
										"Name":"测试接口3",
										"InputParam":{"key9":"val9","key0":"val0"},
										"OutputParam":{"key11":"val11","key12":"va12"}

									},
								],
								"Method":"Java/C"
							}
						]

					}
				) //end JSON.stringify
			}; //end pluginTestCfg
			console_log(JSON.stringify(pluginTestCfg));
			cfgHandler.debugGetCfg(pluginTestCfg, "this.showAllTestItems");
		}
	}
	
	/*show testTerm list*/
	this.showAllTestItems = function(jsonCfg)
	{
		console_log(JSON.stringify(jsonCfg));
		this.testItemsTemplate = jsonCfg.TestItems;
		this.testItems = $.parseJSON(JSON.stringify(this.testItemsTemplate));
		//this.recentTestTermCfgArr = jsonUtil.createJsonObject(jsonUtil.parseString(jsonCfg.TestTermList));
		//this.recentTestTermCfgArr = jsonCfg.TestTermList;
		var testItemListText = '';
		$.each(this.testItemsTemplate, function(idx, testItem) {
			testItemListText += '<tr class="CLS_tableContentData">\
	            <td>\
	              <input type="checkbox" name="testTerm" id="PLUGIN_CHK_testTerm_' + idx + '" data-idx="' + idx + '"/>\
	            </td>\
	            <td>\
	              <label class="font_chara" for="PLUGIN_CHK_testTerm_' + idx + '">' + testItem.Name + '</label>\
	            </td>\
	            <td>\
	              <a href="#" class="blueLinks PLUGIN_CLS_setParam" data-idx="' + idx + '">设置参数</a>\
	            </td>\
				<td id="PLUGIN_TD_testResult_'+ idx +'"></td>\
            	<td><input type="button"  value="详细信息" class="PLUGIN_CLS_detailInfo" id="PLUGIN_BUTTON_detailInfo_'+idx+'" disabled/></td>\
	          </tr>';
		});
		
		$("#PLUGIN_TBODY_testList").html(testItemListText);
		$('#PLUGIN_TB_testList .CLS_tableContentData:nth-child(2n)').children('td').css('background', '#fff');
		$('#PLUGIN_TB_testList .CLS_tableContentData:nth-child(2n+1)').children('td').css('background', '#f3f3f3');
		
		//regist click event
		$(".PLUGIN_CLS_detailInfo").click(function(){
			var itemIdx = this.id.replace("PLUGIN_BUTTON_detailInfo_", "");
			currentPageInst.showTestResult(parseInt(itemIdx));
			$("#PLUIGN_DIV_testDetail").show();
		});
	}

	
	this.updateCfgHook = function(jsonCfg) {
	}

	this.updateDataTypeHook = function() {
		dataTypeHandler.processDataText(); /*refresh dataText*/
	}

	/*format json data*/
	this.jsFormat = function(id, val) {
			$("#" + id).html(val);
			var r = $("#" + id).html();
			r = r.replace(/^\s+/, '');
			if (r && r.charAt(0) === '<') {
				r = style_html(r, 4, ' ', 80);
			} else {
				r = js_beautify(r, 4, ' ');
			}
			$("#" + id).html(r);
	}

	this.showTestTermParam = function(idx) {
		this.jsFormat("PLUGIN_TEXTA_ParamTemplate", JSON.stringify(this.testItems[idx].SubItems));
		this.jsFormat("PLUGIN_TEXTA_setParam", JSON.stringify(this.testItems[idx].SubItems));
	}

	this.setTestItemParam = function(idx) {
		var jsonStr = $("#PLUGIN_TEXTA_setParam").val();
		jsonStr = jsonStr.replace(/(\n|\r|(\r\n)|(\u0085)|(\u2028)|(\u2029))/g, "");
		this.testItems[idx].SubItems = $.parseJSON(jsonStr);
	}

	this.submitTest = function() {
		// var testTermArr = jsonUtil.createJsonArray();
		this.currTestItemIdx = -1; //init currTestItemIdx
		if ($("input[name='testTerm']:checked").length > 0) {
			$("#PLUIGN_BTN_testDetail").html("停止");
			$("#PLUIGN_BTN_testDetail").attr("data-value", "stop");
			$("#PLUIGN_DIV_testDetail").show();
			var testTerms = $("input[name='testTerm']");
			$.each(testTerms, function(idx, testTerm) {
				if ($(this).prop("checked")) {
					//$(this).parent("td").parent("tr").children("td:last").html("");
					var idx = $(this).attr("data-idx");
					currentPageInst.testItemsArray.push(currentPageInst.testItems[idx]);
					currentPageInst.testItemIdxArray.push(idx);
				}
			});
		
			console_log(JSON.stringify(currentPageInst.testItemsArray));
			this.testingFlag = true;
			$(".popupBgFilter").removeClass("hide");
			this.startTest(0); //start first test item
			return;
		} else {
			alert("请选择要测试的项！");
		}
	}
	
	this.clearMonitorTimer = function() {
		if (null != this.monitorTimeHandler)
		{
			clearInterval(this.monitorTimeHandler);
		}
	}
	
	this.stopApiThroughPutTest = function(){
		var postData = {};
		postData.CmdType = "EXECUTE_TEST";
		var testItemCfg = {};
		testItemCfg.Name = "StopApiThroughPut Test";
		postData.TestItem = testItemCfg;
		cfgHandler.setCfg(servletName, postData, "this.processStopTest");
	}
	
	this.stopUsbTaskTest = function(){
		var postData = {};
		postData.CmdType = "EXECUTE_TEST";
		var testItemCfg = {};
		testItemCfg.Name = "StopUsbTask Test";
		postData.TestItem = testItemCfg;
		cfgHandler.setCfg(servletName, postData, "this.processStopTest");
	}
	
	this.processUsbTaskTest = function(jsonCfg){
		
		var subItems = jsonCfg.TestResult.SubItems;
		if(subItems&&subItems.length>0)
		{
			this.tip = subItems[0].ActualOutputParam;
			console_log("info:"+this.tip);
			this.setTip(this.tip);
			$("#PLUIGN_SPAN_testInfo_"+this.getCurrTestItemIdx()).html(this.tip);
			//alert(this.tip);
		}
	}
	
	this.setTip = function(info)
	{
		if(info.indexOf("Insert Usb Serial Device")>-1||info.indexOf("Insert Serial Device")>-1)
		{
			this.tip = "请插入USB Serial 设备...";
		}else if(info.indexOf("register USB Serial device success")>-1)
		{
			this.tip = "注册USB Serial设备成功";
		}else if(info.indexOf("insert USB Serial device success")>-1)
		{
			this.tip = "检测USB Serial设备插入成功";
		}else if(info.indexOf("lock USB Serial device success")>-1)
		{
			this.tip = "锁定USB Serial设备成功";
		}else if(info.indexOf("lock USB Serial device fail")>-1)
		{
			this.tip = "锁定USB Serial设备失败";
		}else if(info.indexOf("write message to USB Serial device success")>-1)
		{
			this.tip = "向USB Serial设备写入数据成功";
		}else if(info.indexOf("write message to USB Serial device fail")>-1)
		{
			this.tip = "向USB Serial设备写入数据失败";
		}else if(info.indexOf("USB Serial device is availbale")>-1)
		{
			this.tip = "检测USB Serial设备数据可用";
		}else if(info.indexOf("USB Serial device is not  availbale")>-1)
		{
			this.tip = "检测USB Serial设备数据不可用";
		}else if(info.indexOf("read message from USB Serial success")>-1)
		{
			this.tip = "从USB Serial设备读取数据成功";
		}else if(info.indexOf("read message from USB Serial fail")>-1)//close USB Serial success 
		{
			this.tip = "从USB Serial设备读取数据失败";
		}else if(info.indexOf("close USB Serial success")>-1)
		{
			this.tip = "关闭USB Serial设备成功";
		}else if(info.indexOf("close USB Serial fail")>-1)
		{
			this.tip = "关闭USB Serial设备失败";
		}else if(info.indexOf("Please Pull Usb Serial Device")>-1)
		{
			this.tip = "请拔出USB Serial 设备";
		}else if(info.indexOf("Please Insert Usb CDC Device")>-1)
		{
			this.tip = "请插入USB虚拟串口";
		}else if(info.indexOf("Check USB Serial is pulled successing")>-1)
		{
			this.tip = "检测USB Serial设备拔出成功";
		}else if(info.indexOf("Unregister USB Serial success")>-1)
		{
			this.tip = "解注册USB Serial设备成功";
		}else if(info.indexOf("Unregister USB Serial fail")>-1)
		{
			this.tip = "解注册USB Serial设备失败";
		}else if(info.indexOf("Unregister usb_cdc success")>-1)
		{
			this.tip = "解注册USB虚拟串口设备成功";
		}else if(info.indexOf("Unregister usb_cdc fail")>-1)//
		{
			this.tip = "解注册USB虚拟串口设备失败";
		}else if(info.indexOf("register usb_cdc success")>-1)
		{
			this.tip = "注册USB虚拟串口设备成功";
		}else if(info.indexOf("insert usb_cdc success")>-1)
		{
			this.tip = "检测USB虚拟串口设备插入成功";
		}else if(info.indexOf("lock usb_cdc success")>-1)
		{
			this.tip = "锁定USB虚拟串口设备成功";
		}else if(info.indexOf("lock usb_cdc fail")>-1)
		{
			this.tip = "锁定USB虚拟串口设备失败";
		}else if(info.indexOf("write message to usb_cdc success")>-1)
		{
			this.tip = "向USB虚拟串口设备写入数据成功";
		}else if(info.indexOf("write message to usb_cdc fail")>-1)
		{
			this.tip = "向USB虚拟串口设备写入数据失败";
		}else if(info.indexOf("check usb_cdc available success")>-1)
		{
			this.tip = "检测USB虚拟串口设备数据是否可用成功";
		}else if(info.indexOf("check usb_cdc available fail")>-1)
		{
			this.tip = "检测USB虚拟串口设备数据是否可用失败";
		}else if(info.indexOf("read message from usb_cdc success")>-1)
		{
			this.tip = "从USB虚拟串口设备读取数据成功";
		}else if(info.indexOf("read message from usb_cdc fail")>-1)
		{
			this.tip = "从USB虚拟串口设备读取数据失败";
		}else if(info.indexOf("close usb_cdc success")>-1)
		{
			this.tip = "关闭USB虚拟串口设备成功";
		}else if(info.indexOf("close usb_cdc fail")>-1)
		{
			this.tip = "关闭USB虚拟串口设备失败";
		}else if(info.indexOf("Please Pull Usb CDC Device")>-1)
		{
			this.tip = "请拔出USB虚拟串口";
		}else if(info.indexOf("Check usb_cdc is pulled successing")>-1)
		{
			this.tip = "检测USB虚拟串口设备拔出成功";
		}else if(info.indexOf("Please Insert Usb Storage Device")>-1)
		{
			this.tip = "请插入USB存储设备";
		}else if(info.indexOf("regeister USB Storage device success")>-1)
		{
			this.tip = "注册USB Storage设备成功";
		}else if(info.indexOf("insert USB Storage device succes")>-1)
		{
			this.tip = "检测USB Storage设备插入成功";
		}else if(info.indexOf("lock USB Storage device success")>-1)
		{
			this.tip = "锁定USB Storage设备成功";
		}else if(info.indexOf("lock USB Storage device fail")>-1)
		{
			this.tip = "锁定USB Storage设备失败";
		}else if(info.indexOf("achieve information from USB Storage access")>-1)
		{
			info = info.replace("achieve information from USB Storage access", "获取存储设备信息成功:");
			info = info.replace("storagePath", "设备存储根目录");
			info = info.replace("totalStorage", ";设备总容量大小");
			this.tip = info.replace("validStorage", "设备可用空间大小");
			//this.tip = "获取存储设备信息成功:<br/>" + info.substring("achieve information from USB Storage access".length, info.length);
		}else if(info.indexOf("achieve information from USB Storage fail")>-1)
		{
			this.tip = "获取存储设备信息失败";
		}else if(info.indexOf("write message to USB Storage success")>-1) 
		{
			this.tip = "向USB Storage设备写入数据成功";
		}else if(info.indexOf("write message to USB Storage fail")>-1)
		{
			this.tip = "向USB Storage设备写入数据失败";
		}else if(info.indexOf("check USB Storage available success")>-1)
		{
			this.tip = "‘检测USB Storage设备数据是否可用’成功";
		}else if(info.indexOf("check USB Storage available fail")>-1)
		{
			this.tip = "‘检测USB Storage设备数据是否可用’失败";
		}else if(info.indexOf("read message from USB Storage success")>-1)
		{
			this.tip = "‘从USB Storage设备读取数据’成功";
		}else if(info.indexOf("read message from USB Storage fail")>-1)
		{
			this.tip = "‘从USB Storage设备读取数据’失败";
		}else if(info.indexOf("close USB Storage success")>-1)//
		{
			this.tip = "'关闭USB Storage设备'成功";
		}else if(info.indexOf("close USB Storage fail")>-1)
		{
			this.tip = "'关闭USB Storage设备'失败";
		}else if(info.indexOf("Please Pull Usb Storage Device")>-1)
		{
			this.tip = "请拔出USB存储设备";
		}else if(info.indexOf("Check USB Storage is pulled successing")>-1)
		{
			this.tip = "‘检测USB Storage设备拔出事件’成功";
		}else if(info.indexOf("Unregister USB Storage success")>-1)//
		{
			this.tip = "‘解注册USB Storage设备’成功";
		}else if(info.indexOf("Unregister USB Storage fail")>-1)
		{
			this.tip = "‘解注册USB Storage设备’失败";
		}else if(info.indexOf("Please Pull Unknow Type Device")>-1)
		{
			this.tip = "请拔出不在此用例测试的设备";
		}
	}
	
	//开始测试
	this.startTest = function(testIdx) {
		var newTestItemFlag = false;
		
		if (testIdx >= this.testItemsArray.length)
		{
			this.testingFlag = false;
			//clear monitorTimeHandler
			this.clearMonitorTimer();
			
			//
			$("#PLUIGN_BTN_testDetail").html("关闭");
			$("#PLUIGN_BTN_testDetail").attr("data-value", "close");
			
			alert("测试结束！");
			console_log("测试结束！");
			$(".popupBgFilter").addClass("hide");
			return;
		}
		
		$("#PLUGIN_subItemDetailCompParamLeft").html("");
		
		if (this.currTestItemIdx != testIdx)
		{
			newTestItemFlag = true;
		}
		this.currTestItemIdx = testIdx;
		
		var testItemCfg = $.parseJSON(JSON.stringify(this.testItemsArray[testIdx]));
		var postData = {};
		postData.CmdType = "EXECUTE_TEST";
		postData.TestItem = testItemCfg;
		if (newTestItemFlag)
		{
			//first query
			testItemCfg.SubItemName = "";
            this.currTestSubItemName = this.testItemsArray[this.currTestItemIdx].SubItems[0].Name;//set first subItemName
		}
		else
		{
			testItemCfg.SubItemName = this.currTestSubItemName;
		}
		console_log("执行测试>>postData"+JSON.stringify(postData));
		
		cfgHandler.setCfg(servletName, postData, "this.processExecuteTestCmdResp");
		
		if (newTestItemFlag)
		{
			//start new test item, init 
			this.queryId = 0;
			this.updateTestReusult(null);
		}
		
		//monitor, retry if not response!
		this.clearMonitorTimer();
		
		this.monitorTimeHandler = setInterval(function(){
			cfgHandler.setCfg(servletName, postData, "this.processExecuteTestCmdResp");
		}, 20000);
		
		/*
		cfgHandler.setCfg(servletName, postData, null);
		
		this.updateTestReusult(null);
		setTimeout(function(){currentPageInst.queryTestResult()}, 5000);
		*/
	}
	
	this.processExecuteTestCmdResp = function(postData) {
		if (!this.testingFlag)
		{
			return;
		}
		setTimeout(function(){currentPageInst.queryTestResult()}, 5000);
	}

	this.queryTestResult = function() {
		if (!this.testingFlag)
		{
			return;
		}
		var queryData = {};
		queryData.CmdType = "QUERY_TEST_RESULT";
		queryData.ItemName = this.testItemsArray[this.currTestItemIdx].Name;
		queryData.SubItemName = this.currTestSubItemName;
		queryData.SyncFlag = this.syncFlag;
		queryData.QueryId = this.queryId;
		queryData.Method = this.testItemsArray[this.currTestItemIdx].Method;
		
		if (!this.debugEnable) {
			if (!this.sendIdEnsureFlag)
			{
				console_log("执行测试>>queryData"+JSON.stringify(queryData));
				cfgHandler.setCfg(servletName, queryData, "this.updateTestReusult");
			}
			else
			{
				console_log("Send Test over ensure msg!!");
				cfgHandler.setCfg(servletName, queryData, null);
				this.sendIdEnsureFlag = false;
				return;
			}
		}else{
			var pluginTestCfg = {
				"return_Parameter":JSON.stringify(
					{
						"CmdType":"QUERY_TEST_RESUILT",
						"Status":"0",
						"TestResult":{
							"Info":"这里是提示信息",
							"CurrSubItemName":"测试接口3",
							"SubItems":[
									{
										"Name":"测试接口１",
										"Result":1,
										"ActualOutputParam":{"key3":"val3","key4":"val4"}

									},
									{
										"Name":"测试接口2",
										"Result":-1,
										"ActualOutputParam":{"key7":"val8","key8":"val9"}

									},
									{
										"Name":"测试接口3",
										"Result":1,
										"ActualOutputParam":{"key11":"val11","key12":"va12"}

									},
									{
										"Name":"USB Dongle测试",
										"Result":1,
										"ActualOutputParam":{"key11":"val11","key12":"va12"}

									},
							], //end SubItems
							"Result":1,
							"NextQueryTime":5000
						} //end testResult
					}
				)
			};
			
			cfgHandler.debugGetCfg(pluginTestCfg, "this.updateTestReusult");
		}
		/*
		setInterval(function() {
			cfgHandler.setCfg(servletName, postData, "this.showTestResult");
		}, 5000);
		*/
		this.clearMonitorTimer();
		this.monitorTimeHandler = setInterval(function(){currentPageInst.queryTestResult();}, 20000);
		
	}
	
	this.getCurrTestItemIdx = function()
	{
		return this.testItemIdxArray[this.currTestItemIdx];
	}
	
	this.updateTestReusult = function(jsonCfg) {
		var testItemCfg = this.testItemsArray[this.currTestItemIdx];
		
		if (null == jsonCfg)
		{
			//init 
			if ($("#PLUIGN_DIV_testDetail_"+this.getCurrTestItemIdx())[0])
			{
				$("#PLUIGN_DIV_testDetail_"+this.getCurrTestItemIdx()).remove();
			}
			
			if(testItemCfg.Name=="API ThroughPut Test"){
				this.tip="请使用 testCenter工具进行测试";
			}else if ("UsbService Event Test"==testItemCfg.Name){
				this.tip="请接入usb";
			}else if (testItemCfg.Name=="VOIP Event Test"){
				this.tip="请接入语音设备";
			}else if (testItemCfg.Name=="LANHost Event Test"){
				this.tip="请接入下挂设备";
			}else if(testItemCfg.Name=="Traffic Mirror Event Test"){
				this.tip="请使用wireshark capture工具进行测试";
			}else if(testItemCfg.Name=="Traffic Monitoring Event Test"){
				this.tip="请访问相关网站进行测试";
			}else{
				this.tip="正在测试,请稍等..."
			}
			
			var testResultDiv = '<div id="PLUIGN_DIV_testDetail_'+this.getCurrTestItemIdx()+ '">';
				testResultDiv += '<div class="PLUIGN_DIV_testDetailTitle font_bold font_large">'+testItemCfg.Name+'</div>';
				testResultDiv += '<hr /><p>提示信息:<span id="PLUIGN_SPAN_testInfo_'+this.getCurrTestItemIdx()+'">'+this.tip+'</span></p>';
				testResultDiv += '<ul>';
			
				//for sub test items
				$.each(testItemCfg.SubItems, function(idx, subItem){
					testResultDiv += '<li test-status="init" id="PLUGIN_LI_subTestItem_'+currentPageInst.getCurrTestItemIdx()+'_'+idx+'">'+subItem.Name+'&nbsp;<a href="javascript:void(0)" class="PLUGIN_CLS_subItemDetailBtn right" data-idx='+currentPageInst.getCurrTestItemIdx()+'></a></li>';
				});
			
				testResultDiv += '</ul> <hr/></div>';
			$("#PLUIGN_DIV_testDetail").append(testResultDiv);
			
			var maxHeight = $(window).height() - getCssPropNum($("#PLUIGN_DIV_testDetail"), "top");
			if ($("#PLUIGN_DIV_testDetail").height() > maxHeight)
			{
				$("#PLUIGN_DIV_testDetail").height(maxHeight-30);
			}
			
			$(".PLUGIN_CLS_subItemDetailBtn").click(function(){
				currentPageInst.processSubTestItemDetailInfo(this);
			});
		
			this.showTestResult(this.getCurrTestItemIdx());
			this.testStatusSwitch("PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_0", 0); //testing
			
			/*if(testItemCfg.Name=="UsbServiceTask Test")
			{
				var testRestult = jsonCfg.TestResult;
				var subItems = testRestult.SubItems;
				if(subItems&&subItems.length>0){
					this.tip = subItems[0].ActualOutputParam;
					$("#PLUIGN_SPAN_testInfo_"+this.getCurrTestItemIdx()).value = this.tip;
				}
				
			}*/
		}
		else
		{
			
			//upate
			var testRestult = jsonCfg.TestResult;
			if (this.testItemsArray[this.currTestItemIdx].Name != testRestult.CurrItemName)
			{
				console_log("正在测试"+this.testItemsArray[this.currTestItemIdx].Name+",但是收到不匹配项"+testRestult.CurrItemName+";忽略本次查询结果!");
				return;
			}
			$("#PLUIGN_SPAN_testInfo_"+this.getCurrTestItemIdx()).fadeOut("fast");
			$("#PLUIGN_SPAN_testInfo_"+this.getCurrTestItemIdx()).html(testRestult.Info);
			$("#PLUIGN_SPAN_testInfo_"+this.getCurrTestItemIdx()).fadeIn("slow");
			this.syncFlag = testRestult.SyncFlag;
			this.queryId = testRestult.QueryId;
			
			this.currTestSubItemName = testRestult.CurrSubItemName;
			for (var i = 0; i < testRestult.SubItems.length; i++)
			{
				var found = false;
				for (var j  = 0; j < testItemCfg.SubItems.length; j++)
				{
					if (testItemCfg.SubItems[j].Name == testRestult.SubItems[i].Name)
					{
						this.testStatusSwitch("PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j, testRestult.SubItems[i].Result);
						$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").attr("data-actualOutputParam", JSON.stringify(testRestult.SubItems[i].ActualOutputParam));
						
						$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").attr("data-outputParam", JSON.stringify(testRestult.SubItems[i].OutputParam));
						$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").attr("data-failReason", testRestult.SubItems[i].FailReason);
						if (testRestult.SubItems[i].Result == 1)
						{
							$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").html("&radic;");
							$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").css("color","green");
						}
						else if (testRestult.SubItems[i].Result == -1)
						{
							$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").html("X");
							$("#PLUGIN_LI_subTestItem_"+this.getCurrTestItemIdx()+"_"+j+" a").css("color","red");
						}
						found = true;
					}
				} /* end for (var j = 0 ...) */

				if (!found)
				{
					alert("收到不匹配测试项："+testRestult.SubItems[i].Name);
					console_log("收到不匹配测试项："+testRestult.SubItems[i].Name);
					//? need start next test item !
				}
			}
			
			if (testRestult.Result != 0)
			{
				//大项测试结束.
				//send ensure query to tell plugin
				this.sendIdEnsureFlag = true;
				this.queryTestResult(); 
				
				$("#PLUGIN_BUTTON_detailInfo_"+this.getCurrTestItemIdx()).attr("disabled", false);
				
				if (testRestult.Result == -1)
				{
					$("#PLUGIN_TD_testResult_"+this.getCurrTestItemIdx()).html("<font color='green'>结束</font>");
				}
				else if (testRestult.Result == 1)
				{
					$("#PLUGIN_TD_testResult_"+this.getCurrTestItemIdx()).html("<font color='green'>结束</font>");
				}
				
				//开启新的测试
				setTimeout(function(){
					currentPageInst.startTest(currentPageInst.currTestItemIdx+1);
				}, 1000);
			}
			else
			{
				if (testRestult.RestartTest == "1")
				{
					if (this.syncFlag == "1")
					{
						//send last query for sync flag
						this.queryTestResult();
					}
					// need resend start execute!!
					this.clearMonitorTimer(); //stop query first;
					setTimeout(function(){currentPageInst.startTest(currentPageInst.currTestItemIdx);}, testRestult.NextQueryTime);
				}
				else
				{
					//执行下一次查询　
					console_log("Start next query after "+testRestult.NextQueryTime+"ms");
					this.queryTimeHandler = setTimeout(function(){currentPageInst.queryTestResult()}, testRestult.NextQueryTime);
				}
			}
			
		}//end else
		
		//
		
	}

	/*show test result*/
	this.showTestResult = function(itemIdx) {
		for (var i = 0; i < this.testItems.length; i++)
		{
			if (i == itemIdx)
			{
				$("#PLUIGN_DIV_testDetail_"+i).show();
			}
			else
			{
				$("#PLUIGN_DIV_testDetail_"+i).hide();
			}
		}
		
	}
	
	this.testStatusSwitch = function(elmId, result) {
		/* remove all impossible class */
		$("#"+elmId).removeClass("PLUGIN_LI_testing");
		$("#"+elmId).removeClass("PLUGIN_LI_testSucess");
		$("#"+elmId).removeClass("PLUGIN_LI_testFail");
		if (result == 0)
		{
			$("#"+elmId).addClass("PLUGIN_LI_testing");
		}
		else if (result == -1)
		{
			$("#"+elmId).addClass("PLUGIN_LI_testFail");
		}
		else if (result == 1)
		{
			$("#"+elmId).addClass("PLUGIN_LI_testSucess");
		}
	}

	this.matchParamCfg = function(idx, key) {
		this.jsFormat("PLUGIN_TEXTA_ParamTemplate", jsonUtil.parseString(this.testTermCfgArr[idx][key]));
		this.jsFormat("PLUGIN_TEXTA_setParam", jsonUtil.parseString(this.recentTestTermCfgArr[idx][key]));
	}

	/*show fali detail information*/
	this.showFailDetail = function(idx) {
		this.jsFormat("PLUGIN_TEXTA_ParamTemplate", jsonUtil.parseString(this.recentTestTermCfgArr[this.curIdx].OutputParam));
		this.jsFormat("PLUGIN_TEXTA_setParam", jsonUtil.parseString(this.curExecuteJsonCfg[idx]));
	}

	// show textarea title
	this.showTextaTitle = function(title1, title2) {
		$("#PLUGIN_P_templateTitle").html(title1);
		$("#PLUGIN_P_setParamTitle").html(title2);
	}
	
	this.processSubTestItemDetailInfo = function(elm) {
		if ($(elm).attr("data-actualOutputParam"))
		{
			//compare outputParam
			$("#PLUGIN_DIV_subItemDetailInfo").show();
			this.jsFormat("PLUGIN_subItemDetailCompParamLeft",$(elm).attr("data-outputParam"));
			this.jsFormat("PLUGIN_subItemDetailCompParamRight",$(elm).attr("data-actualOutputParam"));
			//$("#PLUGIN_subItemDetailCompParamRight").html($(elm).attr("data-actualOutputParam"));
			if ($(elm).attr("data-failReason") != "undefined")
			{
				$("#PLUGIN_subItemFailReason").html("错误原因："+$(elm).attr("data-failReason"));
			}
			else
			{
				$("#PLUGIN_subItemFailReason").html("");
			}
		}
	}
	
	this.processTestDetailBtnEvent = function(elm) {
		if ($(elm).attr("data-value") == "stop")
		{
			if (confirm("正在执行测试，确定停止？"))
			{
				//执行停止
				if(this.currTestSubItemName=="API ThroughPut Test"){
					this.stopApiThroughPutTest();
				}
				if(this.currTestSubItemName=="StopUsbTask Test"){
					this.stopUsbTaskTest();
				}
				this.testingFlag = false;
				this.clearMonitorTimer();
				clearTimeout(this.queryTimeHandler);
				$("#PLUGIN_TD_testResult_"+this.getCurrTestItemIdx()).html("<font>中止</font>");
				$("#PLUIGN_DIV_testDetail").hide();
				
				$("#PLUIGN_BTN_testDetail").html("关闭");
				$("#PLUIGN_BTN_testDetail").attr("data-value", "close");
				$(".popupBgFilter").addClass("hide");
				
			}
			else
			{
				return;
			}
		}
		else if ($(elm).attr("data-value") == "close")
		{
			$("#PLUIGN_DIV_testDetail").hide();
		}
	}

	this.registerEvent = function() {

		$("body").on("click", ".PLUGIN_CLS_setParam", function(e) {
			e.preventDefault();
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_setParam").removeClass("hide")
			currentPageInst.curIdx = $(this).attr("data-idx");
			currentPageInst.showTestTermParam(currentPageInst.curIdx);
			
			//currentPageInst.showTextaTitle(langJsonObj.inputParamTemplate, langJsonObj.setInputParam);
			$("#Btn_cancel").show();
			$(".PLUGIN_CLS_Param").removeAttr("disabled");
		});

		$("body").on("click", ".PLUGIN_CLS_reviewDetail", function(e) {
			e.preventDefault();
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_setParam").removeClass("hide")
			var _idx = $(this).attr("data-idx");
			currentPageInst.curIdx = $(this).attr("data-num");
			//console_log('错误参数下标：'+_idx);
			currentPageInst.showFailDetail(_idx);
			currentPageInst.showTextaTitle(langJsonObj.expectOutputParam, langJsonObj.trueOutputParam);
			$("#Btn_cancel").hide();
			$(".PLUGIN_CLS_Param").attr("disabled", "disabled");
		});

		$("#Btn_apply").click(function(e) {
			e.preventDefault();
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_setParam").addClass("hide");
			currentPageInst.IsEdited = true;
			currentPageInst.setTestItemParam(currentPageInst.curIdx, currentPageInst.curKey);
		});

		$("#Btn_cancel,#PLUGIN_A_closePopup,#Btn_close").click(function(e) {
			e.preventDefault();
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_setParam,#PLUGIN_DIV_showAllParam").addClass("hide");
		});

		$("body").on("mouseover", "#PLUGIN_TB_testList .CLS_tableContentData", function() {
			$(this).children('td').css('background', '#fcf7d4');
		});

		$("body").on("mouseout", "#PLUGIN_TB_testList .CLS_tableContentData", function() {
			$('#PLUGIN_TB_testList .CLS_tableContentData:nth-child(2n)').children('td').css('background', '#fff');
			$('#PLUGIN_TB_testList .CLS_tableContentData:nth-child(2n+1)').children('td').css('background', '#f3f3f3');
		});

		//点击开始测试按钮
		$("#Btn_startTest").click(function() {
			//currentPageInst.curExecuteJsonCfg = [];
			currentPageInst.testItemsArray = new Array(); //init it
			currentPageInst.testItemIdxArray = new Array(); //init it
			currentPageInst.submitTest();
		});
		
		$("#Btn_exportParam").click(function(e) {
			e.preventDefault();
			$("#Btn_apply_exportParam").hide();
			$("#Btn_close").show();
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_showAllParam").removeClass("hide");
			// console_log('导出数据：' + jsonUtil.parseString(currentPageInst.recentTestTermCfgArr));
			
			currentPageInst.jsFormat("PLUGIN_TEXTA_showAllParam", JSON.stringify(currentPageInst.testItems));
			$("#PLUGIN_TEXTA_showAllParam")[0].select();
			$("#PLUGIN_TEXTA_showAllParam").attr("readonly", "true");
		});

		$("#Btn_importParam").click(function(e) {
			e.preventDefault();
			$("#Btn_apply_exportParam").show();
			$("#Btn_close").hide();
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_showAllParam").removeClass("hide");
			$("#PLUGIN_P_paramTitle").html("导入全部参数");
			$("#PLUGIN_TEXTA_showAllParam").removeAttr("readonly");
			$("#PLUGIN_TEXTA_showAllParam").val("");
		});
		

		$("#Btn_apply_exportParam").click(function(e) {
			e.preventDefault();
			var jsonStr = $("#PLUGIN_TEXTA_showAllParam").val();
			jsonStr = jsonStr.replace(/(\n|\r|(\r\n)|(\u0085)|(\u2028)|(\u2029))/g, "");
			try{
			 	var allTermParam = $.parseJSON(jsonStr);
				currentPageInst.testItems = allTermParam;
			}catch(e){alert("格式非法!");return;};
			// console_log('导入数据：' + jsonUtil.parseString(currentPageInst.recentTestTermCfgArr));
			$(".popupBgFilter,#PLUGIN_DIV_popup,#PLUGIN_DIV_showAllParam").addClass("hide");
		});
		
		$("#PLUIGN_BTN_testDetail").click(function(){currentPageInst.processTestDetailBtnEvent(this);});
		
		$("#PLUGIN_BTN_subItemDetailInfoClose").click(function(){$("#PLUGIN_DIV_subItemDetailInfo").hide();});

		$("body").on("click", "#PLUGIN_CHK_allTestTerm", function() {
			$("input[name='testTerm']").prop("checked", $(this).prop("checked"));
		});

		$("body").on("click", "input[name='testTerm']", function() {
			$.each($("input[name='testTerm']"), function(idx, elm) {
				if (!$(elm).prop("checked")) {
					$("#PLUGIN_CHK_allTestTerm").prop("checked", false);
				}

				if ($("input[name='testTerm']:checked").length == $("input[name='testTerm']").length) {
					$("#PLUGIN_CHK_allTestTerm").prop("checked", true);
				}
			});
		});
		
		$("body").on("click", "#PLUGIN_BUTTON_platformEnsure", function(){
			//reset sevlet name
			servletName = "https://" + $("#PLUGIN_platformAppUrl").val() + "/plugin/post?token=" + $("#PLUGIN_platformToken").val() + "&MAC=" + $("#PLUGIN_platformMac").val() + "&PluginName="+$("#PLUGIN_platformName").val()+"&Version="+$("#PLUGIN_platformVer").val()+"&decode=1";
			console_log("--------------------------");
			console_log("servletName:"+servletName);
			$("#PLUGIN_TB_platformDebug").addClass("hide");
			
			//重新加载数据
			currentPageInst.updateAllCfg();
			
		});
		
		$("body").on("click", "#Btn_showLogInfo", function(){
			$("#PLUGIN_TEXTA_testLog").toggle();
		});
	}

} // End Page

//dom ready
$(function() {

	if (null == currentPageInst) {
		if (typeof(Page) != "undefined") {
			currentPageInst = new Page();
		}
	} else {
		currentPageInst = window; //top window
	}
	pageProcessStart();
});

function pageProcessStart() {

	pageProcessLang();
	try {
		if ("function" == typeof(eval(currentPageInst.updateAllCfg))) {
			currentPageInst.updateAllCfg();
		}
	} catch (e) {}

	try {
		if ("function" == typeof(eval(currentPageInst.registerEvent))) {
			currentPageInst.registerEvent();
		}
	} catch (e) {}

}


function pageProcessLang() {
	processDataText();
	try {
		if ("function" == typeof(eval(currentPageInst.initHtmlText))) {
			currentPageInst.initHtmlText();
		}
	} catch (e) {}
}

function processDataText() {
	return;
	/*
	$.each($("[data-text]"), function(idx, elm) {
		var textArray = $(elm).attr("data-text").split('+');
		var dataText = new String();
		for (i = 0; i < textArray.length; i++) {
			dataText += langJsonObj[textArray[i]];

		}

		if (($(elm).prop("tagName") == "INPUT")) {
			$(elm).val(dataText);
		} else {
			$(elm).html(dataText);
		}

		$(elm).removeAttr("data-text");
	});
	*/
}

var cfgHandler = new function() {

	function _getReqJson(cmdType) {
		if (servletName == "apitest/cmcc.cmd")
		{
			return {
			"jsonCfg": "{'RPCMethod':'SetPlug-inParameterValues','Plugin_Name':'"+currentPageInst.pluginName+"','ID':123,'Parameter':{'CmdType':'" + cmdType + "'}}"
			};
		}
		else
		{
		}
	}

	//"jsonCfg": "{\"RPCMethod\":\"SetPlug-inParameterValues\",\"Plugin_Name\":\""+currentPageInst.pluginName+"\",\"ID\":123, \"Parameter\":" + JSON.stringify(postData) + "}"

	function _formatPostData(postData) {
		//console_log("请求数据:"+jsonUtil.parseString(postData));
		if (servletName == "apitest/cmcc.cmd")
		{
			return {
				"jsonCfg": JSON.stringify(postData) 
			};
		}
		else
		{
			return JSON.stringify(postData);
		}
		
	}

	function _proccessCallBack(jsonCfg, callback) {
		if (typeof(callback) != "undefined") {
			if ($.isFunction(callback)) {
				alert("Callback Function should be String!");
				return;
				//callback(currentJsonCfg);
			} else {
				if ($.isFunction(currentPageInst[callback.replace("this.", "")])) {
					currentPageInst[callback.replace("this.", "")](jsonCfg);
				} else {
					alert("Callback Function is Undefined!");
				}
			}
		} else {
			if ($.isFunction(currentPageInst.updateCfgHook)) {
				currentPageInst.updateCfgHook(currentJsonCfg);
			}
		}
	}

	function _processGetCfg(jsonCfg, callback) {
		currentJsonCfg = $.parseJSON(jsonCfg.return_Parameter);
		_proccessCallBack(currentJsonCfg, callback);
	}


	function _getCfg(cmd, cmdType, callback) {
		if (cmd == "" || cmd == "undefined") {
			return;
		}

		var req = (servletName == cmd) ? _getReqJson(cmdType) : null;
		$.post(noCache(cmd),
			req,
			function(jsonObj) {
				try {
					if (_processGetCfg(jsonObj, callback)) {
						//excute sucess!
						//_setLoading(0); //hide
					}
				} catch (e) { /*_setLoading(-1, "getCfg exception!!")*/ }
			},
			"json"
		);
	}

	function _setCfg(cmd, postData, callback) {
		if (cmd == "" || cmd == "undefined") {
			return;
		}

		var _postData = _formatPostData(postData);
				
		$.post(noCache(cmd),
			  _postData,
			function(jsonCfg) {
				console_log("响应数据：" + JSON.stringify(jsonCfg));
				
				if(jsonCfg.TestResult&&jsonCfg.TestResult.CurrSubItemName&&jsonCfg.TestResult.CurrSubItemName=="UsbServiceTask Test")
				{
					currentPageInst.processUsbTaskTest(jsonCfg);
				}
				
				if ((typeof(jsonCfg.Result) != "undefined") && (jsonCfg.Result != 0))
				{
					console_log("配置出错(Result="+jsonCfg.Result+")，忽略此操作！");
					return;
				}
				
				try {
					if (typeof(jsonCfg.return_Parameter) != "undefined")
					{
						_proccessCallBack($.parseJSON(jsonCfg.return_Parameter), callback);
					}
					else
					{
						_proccessCallBack(jsonCfg, callback);
					}
				} catch (e) {}
			},
			"json"
		);
	}

	return {
		getReqJson: function(cmdType) {
			return _getReqJson(cmdType);
		},
		getCfg: function(cmd, cmdType, callback) {
			_getCfg(cmd, cmdType, callback);
		},

		debugGetCfg: function(jsonCfg, callback) {
			_processGetCfg(jsonCfg, callback);
		},

		setCfg: function(cmd, postData, callback) {
			_setCfg(cmd, postData, callback);
		}

	}
}

/* add random time for url，make sure the data is latest */
function noCache(url) {
	var delim = (url.indexOf("?") == -1) ? "?" : "&";
	return url + delim + "timeStamp=" + new Date().getTime();
}

function isJsonValValid(val)
{
	return  !(typeof(jsonVal) == "undefined");
}

function getCssPropNum(jObj, style)
{
	return parseInt(jObj.css(style).replace("px", ""));
}

function console_log(msg)
{
	try {
		console.log(msg);
	}catch(e){};
	
	//add time
	var now=new Date();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	msg = "["+hours+":"+minutes+":"+seconds+"] "+msg;
	$("#PLUGIN_TEXTA_testLog").append(msg);
	$("#PLUGIN_TEXTA_testLog").append("\r\n");
}
