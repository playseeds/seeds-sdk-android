/*
 *		Copyright 2015 MobFox
 *		Licensed under the Apache License, Version 2.0 (the "License");
 *		you may not use this file except in compliance with the License.
 *		You may obtain a copy of the License at
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *		Unless required by applicable law or agreed to in writing, software
 *		distributed under the License is distributed on an "AS IS" BASIS,
 *		WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *		See the License for the specific language governing permissions and
 *		limitations under the License.
 *
 *		Changes:	renamed from RequestAd
 *
 */

package com.playseeds.android.sdk.inappmessaging.inappmessaging;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class RequestInAppMessage<T> {

	InputStream is;

	public T sendRequest(InAppMessageRequest request)
			throws RequestException {

		Log.i("sendCountlyRequest");

		Log.d("Parse Real"); // log.d not working?
		String url = request.countlyUriToString();

		Log.i("InAppMessage RequestPerform HTTP Get Url: " + url);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setSoTimeout(client.getParams(),
				Const.SOCKET_TIMEOUT);
		HttpConnectionParams.setConnectionTimeout(client.getParams(),
				Const.CONNECTION_TIMEOUT);
		HttpProtocolParams.setUserAgent(client.getParams(),
				request.getUserAgent());
		HttpGet get = new HttpGet(url);
		get.setHeader("User-Agent", System.getProperty("http.agent"));
		HttpResponse response;
		try {
			response = client.execute(get);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return parseCountlyJSON(response.getEntity().getContent(), response.getAllHeaders());
			} else {
				throw new RequestException("Server Error. Response code:"
						+ responseCode);
			}
		} catch (RequestException e) {
			throw e;
		} catch (ClientProtocolException e) {
			throw new RequestException("Error in HTTP request", e);
		} catch (IOException e) {
			throw new RequestException("Error in HTTP request", e);
		} catch (Throwable t) {
			throw new RequestException("Error in HTTP request", t);
		}

	}

	abstract T parseCountlyJSON(InputStream inputStream, Header[] headers) throws RequestException;

}