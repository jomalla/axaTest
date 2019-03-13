package com.axa.jordi.test.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.settings.constants.PortalSettingsPortletKeys;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + PortalSettingsPortletKeys.PORTAL_SETTINGS,
				"mvc.command.name=/portal_settings/ntlm",
				"service.ranking:Integer=100"
		},
		service = MVCActionCommand.class
)
public class NTLMFormModificationAction extends BaseMVCActionCommand {

	private static final Log _log = LogFactoryUtil.getLog(NTLMFormModificationAction.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		//Print the current NTLM that is going to be sent and processed
		_log.info("NTLM - Enabled: "+ ParamUtil.getString(actionRequest, "enabled"));
		_log.info("NTLM - Domain Controller: "+ ParamUtil.getString(actionRequest, "domainController"));
		_log.info("NTLM - Domain Controller Name: "+ ParamUtil.getString(actionRequest, "domainControllerName"));
		_log.info("NTLM - Domain: "+ ParamUtil.getString(actionRequest, "domain"));
		_log.info("NTLM - Service Account: "+ ParamUtil.getString(actionRequest, "serviceAccount"));
		_log.info("NTLM - Service Password: "+ ParamUtil.getString(actionRequest, "servicePassword"));
		_log.info("NTLM - Negotiate Flags: "+ ParamUtil.getString(actionRequest, "negotiateFlags"));
		mvcActionCommand.processAction(actionRequest, actionResponse);
	}

	@Reference(target = "(component.name=com.liferay.portal.settings.authentication.ntlm.web.internal.portlet.action.NtlmPortalSettingsFormContributor)")
	protected MVCActionCommand mvcActionCommand;
}




