package com.axa.jordi.test.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.settings.constants.PortalSettingsPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + PortalSettingsPortletKeys.PORTAL_SETTINGS,
				"mvc.command.name=/portal_settings/cas",
				"service.ranking:Integer=100"
		},
		service = MVCActionCommand.class
)
public class CASFormModificationAction extends BaseMVCActionCommand {

	private static final Log _log = LogFactoryUtil.getLog(CASFormModificationAction.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		//Print the current CAS that is going to be sent and processed
		_log.info("CAS - Enabled: "+ ParamUtil.getString(actionRequest, "enabled"));
		_log.info("CAS - Import from LDAP: "+ ParamUtil.getString(actionRequest, "importFromLDAP"));
		_log.info("CAS - Login URL: "+ ParamUtil.getString(actionRequest, "loginURL"));
		_log.info("CAS - Logout on Session Expiration: "+ ParamUtil.getString(actionRequest, "logoutOnSessionExpiration"));
		_log.info("CAS - Logout URL: "+ ParamUtil.getString(actionRequest, "logoutURL"));
		_log.info("CAS - Server Name: "+ ParamUtil.getString(actionRequest, "serverName"));
		_log.info("CAS - Server URL: "+ ParamUtil.getString(actionRequest, "serverURL"));
		_log.info("CAS - No Such User Redirect URL: "+ ParamUtil.getString(actionRequest, "noSuchUserRedirectURL"));
		mvcActionCommand.processAction(actionRequest, actionResponse);
	}

	@Reference(target = "(component.name=com.liferay.portal.settings.authentication.cas.web.internal.portlet.action.CASPortalSettingsFormContributor)")
	protected MVCActionCommand mvcActionCommand;
}




