/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
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
		ExternalDownloadModelRepository.save(externalDownloadModel);

		response.sendRedirect(externalDownloadModel.getFilePath());
	}
	
  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

}
