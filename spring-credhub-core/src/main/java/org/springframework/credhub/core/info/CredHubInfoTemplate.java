/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.credhub.core.info;

import org.springframework.credhub.core.CredHubOperations;
import org.springframework.credhub.core.ExceptionUtils;
import org.springframework.credhub.support.info.VersionInfo;
import org.springframework.http.ResponseEntity;

/**
 * Implements the interaction with CredHub retrieve server information.
 *
 * @author Scott Frederick 
 */
public class CredHubInfoTemplate implements CredHubInfoOperations {
	static final String VERSION_URL_PATH = "/version";

	private CredHubOperations credHubOperations;

	/**
	 * Create a new {@link CredHubInfoTemplate}.
	 *
	 * @param credHubOperations the {@link CredHubOperations} to use for interactions with CredHub
	 */
	public CredHubInfoTemplate(CredHubOperations credHubOperations) {
		this.credHubOperations = credHubOperations;
	}

	/**
	 * Retrieve the version information from the CredHub server.
	 *
	 * @return the server version information
	 */
	@Override
	public VersionInfo version() {
		return credHubOperations.doWithRest(restOperations -> {
			ResponseEntity<VersionInfo> response = restOperations
					.getForEntity(VERSION_URL_PATH, VersionInfo.class);

			ExceptionUtils.throwExceptionOnError(response);

			return response.getBody();
		});
	}
}
