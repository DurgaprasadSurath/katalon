import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable as GlobalVariable

DAIInfo = WS.sendRequest(findTestObject('Eggplant_DAI_Token_Genration'))
println(DAIInfo)
WS.verifyResponseStatusCode(DAIInfo, 200)
def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(DAIInfo.getResponseBodyContent())
GlobalVariable.dai_access_token = result.access_token
println(GlobalVariable.dai_access_token)
ConfigInfo = WS.sendRequest(findTestObject('Trigger_Test_Config', [('Test_Config_Id') : '${Test_Config_Id}']))
WS.verifyResponseStatusCode(ConfigInfo, 200)

