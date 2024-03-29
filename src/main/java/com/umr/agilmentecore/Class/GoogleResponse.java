package com.umr.agilmentecore.Class;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "success", "score", "action", "challenge_ts", "hostname", "error-codes" })
public class GoogleResponse {
	@JsonProperty("success")
	private boolean success;
	@JsonProperty("challenge_ts")
	private String challengeTs;
	@JsonProperty("hostname")
	private String hostname;
	@JsonProperty("score")
	private float score;
	@JsonProperty("action")
	private String action;
	@JsonProperty("error-codes")
	private ErrorCode[] errorCodes;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getChallengeTs() {
		return challengeTs;
	}

	public void setChallengeTs(String challengeTs) {
		this.challengeTs = challengeTs;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ErrorCode[] getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(ErrorCode[] errorCodes) {
		this.errorCodes = errorCodes;
	}

	enum ErrorCode {
		MissingSecret, InvalidSecret, MissingResponse, InvalidResponse, BadRequest, TimeoutOrDuplicate;

		private static Map<String, ErrorCode> errorsMap = new HashMap<>(6);

		static {
			errorsMap.put("missing-input-secret", MissingSecret);
			errorsMap.put("invalid-input-secret", InvalidSecret);
			errorsMap.put("missing-input-response", MissingResponse);
			errorsMap.put("bad-request", InvalidResponse);
			errorsMap.put("invalid-input-response", BadRequest);
			errorsMap.put("timeout-or-duplicate", TimeoutOrDuplicate);
		}

		@JsonCreator
		public static ErrorCode forValue(final String value) {
			return errorsMap.get(value.toLowerCase());
		}
	}

	@JsonIgnore
	public boolean hasClientError() {
		final ErrorCode[] errors = getErrorCodes();
		if (errors == null) {
			return false;
		}
		for (final ErrorCode error : errors) {
			switch (error) {
			case InvalidResponse:
			case MissingResponse:
			case BadRequest:
				return true;
			default:
				break;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "GoogleResponse{" + "success=" + success + ", challengeTs='" + challengeTs + '\'' + ", hostname='"
				+ hostname + '\'' + ", score='" + score + '\'' + ", action='" + action + '\'' + ", errorCodes="
				+ Arrays.toString(errorCodes) + '}';
	}
}
