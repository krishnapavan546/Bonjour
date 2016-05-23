package com.ysl.bonjour.util;

import java.util.List;

public class Constants {
	private Constants() {
		super();		
	}
	public static final String APPLICATION_PATH = Configurator.getString("config_applicationpath"); //$NON-NLS-1$
	public static final String SERVICE_FXML_PATH =Configurator.getString("config_servicecontrollerfxml_path"); //$NON-NLS-1$
	public static final String LOGIN_FXML_PATH = Configurator.getString("config_usercontrollerfxml_path"); //$NON-NLS-1$
	public static final String MAKUP_FXML_PATH = Configurator.getString("config_makeovercontroller_path"); //$NON-NLS-1$
	public static final String BENJOUR_APPLICATION_NAME = Configurator.getString("config_application_name"); //$NON-NLS-1$
	public static final String HANGOUT_URL = Configurator.getString("config_hangout_url"); //$NON-NLS-1$
	public static final String CAPTURED_IMAGE_PATH = Configurator.getString("config_capture_first"); //$NON-NLS-1$
	public static final String CAPTURED_IMAGE_TYPE = Configurator.getString("config_file_extension"); //$NON-NLS-1$
	public static final String USER_NAME = Configurator.getString("config_username"); //$NON-NLS-1$
	public static final String PASSWORD = Configurator.getString("config_password"); //$NON-NLS-1$
	public static final String SMTP_HOST = Configurator.getString("config_host"); //$NON-NLS-1$
	
	public static final List TO_EMAIL_List = null;
	public static final String FROM_EMAIL_ID = Configurator.getString("config_tomailid"); //$NON-NLS-1$
	public static final String SUBJECT = Configurator.getString("config_subject"); //$NON-NLS-1$
	public static final String MAIL_BODY = Configurator.getString("config_mailbody") //$NON-NLS-1$
								+ Configurator.getString("config_signature"); //$NON-NLS-1$
	public static final String MDETAILS_FXML_PATH = Configurator.getString("config_makeup_details_controller_fxml_path"); //$NON-NLS-1$
	public static final String CAPTUREDLATER_IMAGE_PATH = Configurator.getString("config_capturelater"); //$NON-NLS-1$
	
}
