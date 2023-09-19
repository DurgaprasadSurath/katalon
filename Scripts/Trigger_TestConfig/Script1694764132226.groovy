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

DAIInfo = WS.sendRequest(findTestObject('Eggplant_DAI_Token_Genration'))

println(DAIInfo)

WS.verifyResponseStatusCode(DAIInfo, 200)

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(DAIInfo.getResponseBodyContent())

GlobalVariable.dai_access_token = result.access_token

println(GlobalVariable.dai_access_token)

ConfigInfo = WS.sendRequest(findTestObject('Trigger_Test_Config', [('Test_Config_Id') : '${Test_Config_Id}']))

WS.verifyResponseStatusCode(ConfigInfo, 200)

