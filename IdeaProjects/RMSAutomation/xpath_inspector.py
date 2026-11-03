
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement

def get_strong_xpath(driver, element: WebElement) -> str:
    """
    Generates a robust XPath for a given WebElement.
    """
    # Prioritize ID if available and unique
    element_id = element.get_attribute("id")
    if element_id:
        xpath = f'//*[@id="{element_id}"]'
        elements = driver.find_elements(By.XPATH, xpath)
        if len(elements) == 1:
            return xpath

    # Use text content for common tags if specific enough
    text = element.text.strip()
    if text and element.tag_name in ['a', 'button', 'span', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6']:
        xpath = f'//{element.tag_name}[normalize-space()="{text}"]'
        elements = driver.find_elements(By.XPATH, xpath)
        if len(elements) == 1:
            return xpath

    # Fallback to a more complex but still robust XPath
    # This example builds a path based on parent elements.
    # For a real-world scenario, this could be much more sophisticated.
    return get_relative_xpath(element)

def get_relative_xpath(element: WebElement) -> str:
    """
    Generates a relative XPath from the element up to the body tag.
    """
    path = ''
    current = element
    while current.tag_name != 'body':
        parent = current.find_element(By.XPATH, '..')
        siblings = parent.find_elements(By.XPATH, f'./{current.tag_name}')
        if len(siblings) > 1:
            for i, sibling in enumerate(siblings):
                if sibling == current:
                    path = f'/{current.tag_name}[{i+1}]' + path
                    break
        else:
            path = f'/{current.tag_name}' + path
        current = parent
    return '/html/body' + path


def main():
    """
    Main function to inspect elements and generate XPaths.
    """
    # --- Configuration ---
    # 1. Make sure you have selenium installed: pip install selenium
    # 2. Download the appropriate WebDriver for your browser (e.g., chromedriver for Chrome)
    #    and place it in your system's PATH or specify the path here.
    #    Example for Chrome:
    #    service = webdriver.chrome.service.Service(executable_path='/path/to/chromedriver')
    #    driver = webdriver.Chrome(service=service)

    driver = webdriver.Chrome()
    url = "https://stage.palletnow.co/purchase/purchase-indent/create-purchase-indent"
    driver.get(url)

    # It's a good practice to wait for the page to load
    # You might need to log in or perform other actions to get to the desired page state.
    print("Page loaded. You might need to log in manually in the browser window.")
    print("After you have logged in and are on the correct page, press Enter in this console to continue...")
    input()


    print("Inspecting elements...")
    elements = driver.find_elements(By.XPATH, '//*')
    print(f"Found {len(elements)} elements.")

    for element in elements:
        try:
            if element.is_displayed() and element.is_enabled():
                tag = element.tag_name
                text = element.text.strip()
                xpath = get_strong_xpath(driver, element)
                print(f"Tag: {tag}, Text: '{text}', XPath: {xpath}")
        except Exception as e:
            # This can happen if elements become stale
            # print(f"Could not inspect an element: {e}")
            pass

    driver.quit()

if __name__ == "__main__":
    main()
