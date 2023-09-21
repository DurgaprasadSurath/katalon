import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import groovy.json.JsonSlurper

DAIInfo = WS.sendRequest(findTestObject('Eggplant_DAI_Token_Genration'))

WS.verifyResponseStatusCode(DAIInfo, 200)

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(DAIInfo.getResponseBodyContent())

GlobalVariable.dai_access_token = result.access_token

println(GlobalVariable.dai_access_token)

ConfigInfo = WS.sendRequest(findTestObject('Trigger_Test_Config', [('Test_Config_Id') : '${Test_Config_Id}']))

WS.verifyResponseStatusCode(ConfigInfo, 200)

def slurper2 = new groovy.json.JsonSlurper()

def result2 = slurper2.parseText(ConfigInfo.getResponseBodyContent())

GlobalVariable.test_config_instance_id = result2.task_instance_id

println(GlobalVariable.test_config_instance_id)

progress_info = WS.sendRequest(findTestObject('Get_Progress'))

WS.verifyResponseStatusCode(progress_info, 200)

def slurper3 = new groovy.json.JsonSlurper()

def result3 = slurper3.parseText(progress_info.getResponseBodyContent())

GlobalVariable.progress_details = result3.runstatus

while (GlobalVariable.progress_details == 'STARTED') {
    progress_info = WS.sendRequest(findTestObject('Get_Progress'))

    def slurper4 = new groovy.json.JsonSlurper()

    def result4 = slurper4.parseText(progress_info.getResponseBodyContent())

    println(result4)

    GlobalVariable.progress_details = result4.runstatus
}

println(GlobalVariable.progress_details)

run_info = WS.sendRequest(findTestObject('Get_Run_Id'))

def slurper5 = new groovy.json.JsonSlurper()

def result5 = slurper5.parseText(run_info.getResponseBodyContent())

println(result5)

def run_items = result5.items

def run_id_square = run_items.id

println(run_id_square)

run_Id_Square_string = run_id_square.toString()

GlobalVariable.run_id = run_Id_Square_string.replaceAll(/\[|\]/, '')

run_logs_of_Id = WS.sendRequest(findTestObject('Get_Run_Logs'))

println(run_logs_of_Id)

WS.verifyResponseStatusCode(run_logs_of_Id, 200)

def jsonResponse_Run_Id = run_logs_of_Id.getResponseText()

def jsonSlurper = new JsonSlurper()

def jsonObject = jsonSlurper.parseText(jsonResponse_Run_Id)

def elements = jsonObject.allmessages
println(elements)
for (entry in jsonObject) {
	println(entry)
	def message = entry.message
	if (message.contains("Mobile Price")) {
		println("Element Value": message)
		GlobalVariable.MobilePrice = message
		break;
	}
}

WebUI.openBrowser('')

WebUI.navigateToUrl('https://write-box.appspot.com/')

WebUI.sendKeys(findTestObject('Page_STORE/textFieldId'), '${GlobalVariable.MobilePrice}')

WebUI.closeBrowser()