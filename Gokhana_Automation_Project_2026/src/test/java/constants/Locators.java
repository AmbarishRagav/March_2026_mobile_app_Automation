package constants;

/**
 * Central locator repository — all locators in one place.
 * Update locators here without touching page class logic.
 *
 * Locator Types Used:
 * ┌─────────────────────┬────────────────────────────────────────────────────┐
 * │ XPath               │ Most flexible, targets by attribute/hierarchy      │
 * │ Accessibility ID    │ Uses content-desc, fastest for labeled elements    │
 * │ Resource ID         │ Uses resource-id, most stable when available       │
 * └─────────────────────┴────────────────────────────────────────────────────┘
 */
public class Locators {

    // ── Login ─────────────────────────────────────────────────────────────────

    /**
     * XPath — targets EditText by @hint attribute
     */
    public static final String MOBILE_NUMBER_FIELD = "//android.widget.EditText[@hint='Phone number or email']";

    /**
     * XPath — targets CheckBox by partial @content-desc match
     */
    public static final String TERMS_CHECKBOX = "//android.widget.CheckBox[contains(@content-desc,'I agree to the Terms and Conditions')]";

    /**
     * XPath — targets View by exact @content-desc
     */
    public static final String GET_OTP_BUTTON = "//android.view.View[@content-desc='Get OTP']";

    /**
     * XPath — targets EditText as sibling of "Enter OTP" label
     */
    public static final String OTP_INPUT_FIELD = "//android.view.View[@content-desc='Enter OTP']/following-sibling::android.widget.EditText";

    /**
     * XPath — targets Button by @resource-id (full package prefix)
     */
    public static final String NOTIFICATION_ALLOW_BTN = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]";

    /**
     * Accessibility ID — uses content-desc value directly
     */
    public static final String FOOD_COURT_BUTTON = "Food Courts";

    /**
     * Accessibility ID — uses content-desc value directly
     */
    public static final String LATER_BUTTON = "Later";

    // ── Search ────────────────────────────────────────────────────────────────

    /**
     * Accessibility ID — targets ImageView by exact @content-desc from Appium Inspector
     */
    public static final String SEARCH_ICON = "//android.widget.ImageView[@content-desc=\"Search, Double tap to search foodcourts\"]";

    /**
     * Accessibility ID — the search field on the Search screen.
     * Note: class is android.view.View (Flutter rendered), clickable=false.
     * Cannot use sendKeys() directly — must tap by coordinates then type into activeElement().
     */
    public static final String SEARCH_INPUT_FIELD = "Search your foodcourts, Double tap to start typing";

}
