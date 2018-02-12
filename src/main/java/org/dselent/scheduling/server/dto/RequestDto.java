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

	/**
	 * Builder to build {@link RequestDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Integer coursesId;
		private Integer endId;
		private String message;
		private Integer startId;
		private Integer statusId;
		private Integer termsId;
		private Integer userId;

		private Builder() {
		}

		public RequestDto build() {
			return new RequestDto(this);
		}

		public Builder withCoursesId(Integer coursesId) {
			this.coursesId = coursesId;
			return this;
		}

		public Builder withEndId(Integer endId) {
			this.endId = endId;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withStartId(Integer startId) {
			this.startId = startId;
			return this;
		}
		
		public Builder withStatusId(Integer statusId) {
			this.statusId = statusId;
			return this;
		}

		public Builder withTermsId(Integer termsId) {
			this.termsId = termsId;
			return this;
		}

		public Builder withUsersId(Integer userId) {
			this.userId = userId;
			return this;
		}
	}
	/**
	 * Creates builder to build {@link RequestDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	private final Integer coursesId;
	private final Integer endId;
	private final String message;
	private final Integer startId;
	private final Integer statusId;
	private final Integer termsId;
	private final Integer userId;

	@Generated("SparkTools")
	private RequestDto(Builder builder) {
		this.userId = builder.userId;
		this.coursesId = builder.coursesId;
		this.startId = builder.startId;
		this.endId = builder.endId;
		this.termsId = builder.termsId;
		this.statusId = builder.statusId;
		this.message = builder.message;

		if(this.userId == null)
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
		else if(this.statusId == null)
		{
			throw new IllegalStateException("statusId cannot be null");
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
		if (statusId == null) {
			if (other.statusId != null) {
				return false;
			}
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (termsId == null) {
			if (other.termsId != null) {
				return false;
			}
		} else if (!termsId.equals(other.termsId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	public Integer getCoursesId() {
		return coursesId;
	}

	public Integer getEndId() {
		return endId;
	}

	public String getMessage() {
		return message;
	}


	public Integer getStartId() {
		return startId;
	}

	public Integer getTermsId() {
		return termsId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public Integer getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursesId == null) ? 0 : coursesId.hashCode());
		result = prime * result + ((endId == null) ? 0 : endId.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((startId == null) ? 0 : startId.hashCode());
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result + ((termsId == null) ? 0 : termsId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("RequestDto [userId=");
		builder2.append(userId);
		builder2.append(", coursesId=");
		builder2.append(coursesId);
		builder2.append(", startId=");
		builder2.append(startId);
		builder2.append(", endId=");
		builder2.append(endId);
		builder2.append(", termsId=");
		builder2.append(termsId);
		builder2.append(", statusId=");
		builder2.append(statusId);
		builder2.append(", message=");
		builder2.append(message);
		builder2.append("]");
		return builder2.toString();
	}


}
