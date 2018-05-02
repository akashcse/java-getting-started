
package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class Main {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }
  
  @RequestMapping(value = "/external", method = RequestMethod.GET)
	public String addMailTrackingApplication(@RequestParam("instance") String instance, Model model)
			throws Exception {
		ExternalDownloadModel externalDownloadModel = ExternalDownloadModelRepository.findOne(instance);
		
		if (externalDownloadModel != null) {
			String[] path = externalDownloadModel.getFilePath().split("/");
			externalDownloadModel.setFilePath(path[path.length - 1]);
			model.addAttribute("externalDownloadModel", externalDownloadModel);
			return "TurmsAndConditions";
		}
		model.addAttribute("message", "You are not authorize for this service.");
		return "DeadEnd";
	}

	@RequestMapping(value = "/downloadfile", method = RequestMethod.POST, params = "action=download")
	public void downloadFilePage(@ModelAttribute ExternalDownloadModel externalDownloadModel,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		String fileName = externalDownloadModel.getFilePath();
		externalDownloadModel = ExternalDownloadModelRepository.findOne(externalDownloadModel.getExternalId());
		externalDownloadModel.setLastUpdateTime(FormUtil.getArozonaCurrentDateAndTime());
		externalDownloadModel.setAgree(true);
		ExternalDownloadModelRepository.update(externalDownloadModel);

		response.sendRedirect(externalDownloadModel.getFilePath());
	}
}
