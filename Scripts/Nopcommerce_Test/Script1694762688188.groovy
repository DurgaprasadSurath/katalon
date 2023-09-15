import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')



WebUI.navigateToUrl('https://demoblaze.com/')

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_STORE/a_PRODUCT STORE'), 0)



WebUI.verifyElementPresent(findTestObject('Object Repository/Page_STORE/a_Samsung galaxy s6'), 0)



WebUI.click(findTestObject('Object Repository/Page_STORE/a_Samsung galaxy s6'))



WebUI.verifyElementPresent(findTestObject('Object Repository/Page_STORE/strong_Product description'), 0)



WebUI.click(findTestObject('Object Repository/Page_STORE/a_Add to cart'))



WebUI.closeBrowser()