package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 * @author dselent
 *
 */
public class RequestDto {
	
	private final Integer usersId;
	private final Integer coursesId;
	private final Integer startId;
	private final Integer endId;
	private final Integer termsId;
	private final String message;

	@Generated("SparkTools")
	private RequestDto(Builder builder) {
		this.usersId = builder.usersId;
		this.coursesId = builder.coursesId;
		this.startId = builder.startId;
		this.endId = builder.endId;
		this.termsId = builder.termsId;
		this.message = builder.message;
		
		if(this.usersId == null)
		{
			throw new IllegalStateException("usersId cannot be null");
		}
		else if(this.coursesId == null)
		{
			throw new IllegalStateException("coursesId cannot be null");
		}
		else if(this.startId == null)
		{
			throw new IllegalStateException("startId cannot be null");
		}
		else if(this.endId == null)
		{
			throw new IllegalStateException("endId cannot be null");
		}
		else if(this.termsId == null)
		{
			throw new IllegalStateException("termsId cannot be null");
		}
		else if(this.message == null)
		{
			throw new IllegalStateException("message cannot be null");
		}
	}

	public Integer getUsersId() {
		return usersId;
	}
	
	public Integer getCoursesId() {
		return coursesId;
	}
	
	public Integer getStartId() {
		return startId;
	}
	
	public Integer getEndId() {
		return endId;
	}
	
	public Integer getTermsId() {
		return termsId;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursesId == null) ? 0 : coursesId.hashCode());
		result = prime * result + ((endId == null) ? 0 : endId.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((startId == null) ? 0 : startId.hashCode());
		result = prime * result + ((termsId == null) ? 0 : termsId.hashCode());
		result = prime * result + ((usersId == null) ? 0 : usersId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RequestDto)) {
			return false;
		}
		RequestDto other = (RequestDto) obj;
		if (coursesId == null) {
			if (other.coursesId != null) {
				return false;
			}
		} else if (!coursesId.equals(other.coursesId)) {
			return false;
		}
		if (endId == null) {
			if (other.endId != null) {
				return false;
			}
		} else if (!endId.equals(other.endId)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (startId == null) {
			if (other.startId != null) {
				return false;
			}
		} else if (!startId.equals(other.startId)) {
			return false;
		}
		if (termsId == null) {
			if (other.termsId != null) {
				return false;
			}
		} else if (!termsId.equals(other.termsId)) {
			return false;
		}
		if (usersId == null) {
			if (other.usersId != null) {
				return false;
			}
		} else if (!usersId.equals(other.usersId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubmitRequestDto [usersId=");
		builder.append(usersId);
		builder.append(", coursesId=");
		builder.append(coursesId);
		builder.append(", startId=");
		builder.append(startId);
		builder.append(", endId=");
		builder.append(endId);
		builder.append(", termsId=");
		builder.append(termsId);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Creates builder to build {@link RequestDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link RequestDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Integer usersId;
		private Integer coursesId;
		private Integer startId;
		private Integer endId;
		private Integer termsId;
		private String message;

		private Builder() {
		}

		public Builder withUsersId(Integer usersId) {
			this.usersId = usersId;
			return this;
		}

		public Builder withCoursesId(Integer coursesId) {
			this.coursesId = coursesId;
			return this;
		}

		public Builder withStartId(Integer startId) {
			this.startId = startId;
			return this;
		}

		public Builder withEndId(Integer endId) {
			this.endId = endId;
			return this;
		}

		public Builder withTermsId(Integer termsId) {
			this.termsId = termsId;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public RequestDto build() {
			return new RequestDto(this);
		}
	}
	
	
}
