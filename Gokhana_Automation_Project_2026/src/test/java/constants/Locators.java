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

    /** XPath — targets EditText by @hint attribute */
    public static final String MOBILE_NUMBER_FIELD     = "//android.widget.EditText[@hint='Phone number or email']";

    /** XPath — targets CheckBox by partial @content-desc match */
    public static final String TERMS_CHECKBOX          = "//android.widget.CheckBox[contains(@content-desc,'I agree to the Terms and Conditions')]";

    /** XPath — targets View by exact @content-desc */
    public static final String GET_OTP_BUTTON          = "//android.view.View[@content-desc='Get OTP']";

    /** XPath — targets EditText as sibling of "Enter OTP" label */
    public static final String OTP_INPUT_FIELD         = "//android.view.View[@content-desc='Enter OTP']/following-sibling::android.widget.EditText";

    /** XPath — targets Button by @resource-id (full package prefix) */
    public static final String NOTIFICATION_ALLOW_BTN  = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]";

    /** Accessibility ID — uses content-desc value directly */
    public static final String FOOD_COURT_BUTTON       = "Food Courts";

    /** Accessibility ID — uses content-desc value directly */
    public static final String LATER_BUTTON            = "Later";

    // ── Account ───────────────────────────────────────────────────────────────

    /** XPath — targets last ImageView using XPath position function */
    public static final String LAST_IMAGE_VIEW         = "(//android.widget.ImageView)[last()]";

    /** XPath — targets ImageView by exact @content-desc */
    public static final String CORPORATE_CODE          = "//android.widget.ImageView[@content-desc='Corporate Code']";

    /** XPath — targets first EditText by class name only */
    public static final String CORPORATE_CODE_INPUT    = "//android.widget.EditText";

    /** XPath — targets View by exact @content-desc */
    public static final String VALIDATE_BUTTON         = "//android.view.View[@content-desc='Validate']";

    /** XPath — targets Button by exact @content-desc */
    public static final String DELETE_ICON             = "//android.widget.Button[@content-desc='Delete Icon, Double tap to remove corporate code']";

    /** XPath — targets View by exact @content-desc */
    public static final String DELETE_CONFIRMATION     = "//android.view.View[@content-desc='Yes, Delete']";

    /** XPath — targets ImageView by exact @content-desc */
    public static final String DELETE_ACCOUNT          = "//android.widget.ImageView[@content-desc='Delete Account']";

    /** XPath — targets View by exact @content-desc */
    public static final String CONTINUE_BUTTON         = "//android.view.View[@content-desc='Continue']";

    /** XPath — targets View by exact @content-desc */
    public static final String DELETE_ACCOUNT_CONFIRM  = "//android.view.View[@content-desc='Delete']";

    /** XPath — targets first ImageView by exact @content-desc using position index [1] */
    public static final String ADD_FAVOURITES_FIRST    = "(//android.widget.ImageView[@content-desc='Favourite Button, Double tap to add to favourite'])[1]";

    /** XPath — targets second ImageView by exact @content-desc using position index [2] */
    public static final String ADD_FAVOURITES_SECOND   = "(//android.widget.ImageView[@content-desc='Favourite Button, Double tap to add to favourite'])[2]";

    /** XPath — targets ImageView by exact @content-desc */
    public static final String MANAGE_ADDRESS          = "//android.widget.ImageView[@content-desc='Manage Address']";

    /** XPath — targets View by exact @content-desc */
    public static final String ADD_NEW_ADDRESS         = "//android.view.View[@content-desc='Add New Address']";

    /** XPath — targets View by exact @content-desc */
    public static final String USE_CURRENT_LOCATION    = "//android.view.View[@content-desc='Use current locations']";

    /** XPath — targets View by exact @content-desc */
    public static final String CONFIRM_LOCATION        = "//android.view.View[@content-desc='Confirm']";

    /** XPath — targets View by exact @content-desc (input field label) */
    public static final String HOUSE_FLAT_NO           = "//android.view.View[@content-desc='House / Flat No.*, Double tap to start typing']";

    /** XPath — targets View by exact @content-desc (input field label) */
    public static final String APARTMENT_LANDMARK      = "//android.view.View[@content-desc='Apartment/Road/Landmark.*, Double tap to start typing']";

    /** XPath — targets View by exact @content-desc (input field label) */
    public static final String LOCALITY                = "//android.view.View[@content-desc='Locality*, Double tap to start typing']";

    /** XPath — targets View by exact @content-desc */
    public static final String ADD_ADDRESS             = "//android.view.View[@content-desc='Add Address']";

    /** XPath — targets ImageView by exact @content-desc */
    public static final String EDIT_PROFILE            = "//android.widget.ImageView[@content-desc='Edit Profile']";

    /** XPath — targets EditText as child of View with @content-desc label */
    public static final String EMPLOYEE_ID             = "//android.view.View[@content-desc='Employee ID, Double tap to start typing']/android.widget.EditText";

    /** XPath — targets EditText as child of View with @content-desc label */
    public static final String COMPANY_EMAIL           = "//android.view.View[@content-desc='Company email, Double tap to start typing']/android.widget.EditText";

    /** XPath — targets View by exact @content-desc */
    public static final String ADD_DETAILS_BUTTON      = "//android.view.View[@content-desc='Add Details']";

    /** XPath — targets View by exact @content-desc */
    public static final String EDIT_EMPLOYEE_DETAILS   = "//android.view.View[@content-desc='Edit Employee Details']";

    /** XPath — targets EditText as child of View with @content-desc label */
    public static final String CUBICLE_FIELD           = "//android.view.View[@content-desc='Cubicle, Double tap to start typing']/android.widget.EditText";

    /** XPath — targets View by exact @content-desc */
    public static final String UPDATE_COMPANY_DETAILS  = "//android.view.View[@content-desc='Update Company Details']";

    // ── Search ────────────────────────────────────────────────────────────────

    /** XPath — targets View by exact @content-desc */
    public static final String SEARCH_WITHIN_FOODCOURTS = "//android.view.View[@content-desc='Search within foodcourts, Double tap to start typing']";

    /** XPath — targets View by partial @content-desc match using contains() */
    public static final String SEARCH_RESULT            = "//android.view.View[contains(@content-desc, 'Gokhana')]";

    // ── Food Outlets ──────────────────────────────────────────────────────────

    /** XPath — targets View by multiple partial @content-desc matches using and condition */
    public static final String FOOD_COURTS_BROWSE       = "//android.view.View[contains(@content-desc, 'Food Courts') and contains(@content-desc, 'Browse across food courts and enjoy benefits')]";

    /** XPath — targets View by partial @content-desc match using contains() */
    public static final String GOKHANA_FOOD_COURT       = "//android.view.View[contains(@content-desc, 'GoKhana Food Court')]";

    /** XPath — targets View by partial @content-desc match using contains() */
    public static final String GK_BITES                 = "//android.view.View[contains(@content-desc, 'GK Bites')]";

    /** XPath — targets first View by partial @content-desc using position index [1] */
    public static final String ADD_TO_CART              = "(//android.view.View[contains(@content-desc, 'Add')])[1]";

    /** XPath — targets View by exact @content-desc */
    public static final String REMOVE_QUANTITY          = "//android.view.View[@content-desc='Remove One Quantity From Cart']";

    /** XPath — targets ImageView by partial @content-desc match using contains() */
    public static final String VIEW_CART                = "//android.widget.ImageView[contains(@content-desc, 'View Cart')]";
}
