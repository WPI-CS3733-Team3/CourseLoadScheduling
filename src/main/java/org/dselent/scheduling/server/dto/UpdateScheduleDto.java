package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 * @author dselent
 * Modified by Hannah Jauris
 *
 */
public class UpdateScheduleDto {
	private final Integer sectionsId;
	private final Integer termsId;
	private final Integer daysId;
	private final Integer startId;
	private final Integer endId;
	private final Integer facultyId;
	
	@Generated("SparkTools")
	private UpdateScheduleDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		
		this.sectionsId = builder.sectionsId;
		this.termsId = builder.termsId;
		this.daysId = builder.daysId;
		this.startId = builder.startId;
		this.endId = builder.endId;
		this.facultyId = builder.facultyId;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.sectionsId == null)
		{
			throw new IllegalStateException("sectionsId cannot be null");
		}
		else if(this.termsId == null)
		{
			throw new IllegalStateException("termsId cannot be null");
		}
		else if(this.daysId == null)
		{
			throw new IllegalStateException("daysId cannot be null");
		}
		else if(this.startId == null)
		{
			throw new IllegalStateException("startId cannot be null");
		}
		else if(this.endId == null)
		{
			throw new IllegalStateException("endId cannot be null");
		}
		else if(this.facultyId == null)
		{
			throw new IllegalStateException("facultyId cannot be null");
		}
	}

	//getters and setters
	public Integer getSectionsId() {
		return sectionsId;
	}

	public Integer getTermsId() {
		return termsId;
	}

	public Integer getDaysId() {
		return daysId;
	}

	public Integer getStartId() {
		return startId;
	}

	public Integer getEndId() {
		return endId;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	//hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((daysId == null) ? 0 : daysId.hashCode());
		result = prime * result + ((endId == null) ? 0 : endId.hashCode());
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((sectionsId == null) ? 0 : sectionsId.hashCode());
		result = prime * result + ((startId == null) ? 0 : startId.hashCode());
		result = prime * result + ((termsId == null) ? 0 : termsId.hashCode());
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
		if (!(obj instanceof UpdateScheduleDto)) {
			return false;
		}
		UpdateScheduleDto other = (UpdateScheduleDto) obj;
		if (daysId == null) {
			if (other.daysId != null) {
				return false;
			}
		} else if (!daysId.equals(other.daysId)) {
			return false;
		}
		if (endId == null) {
			if (other.endId != null) {
				return false;
			}
		} else if (!endId.equals(other.endId)) {
			return false;
		}
		if (facultyId == null) {
			if (other.facultyId != null) {
				return false;
			}
		} else if (!facultyId.equals(other.facultyId)) {
			return false;
		}
		if (sectionsId == null) {
			if (other.sectionsId != null) {
				return false;
			}
		} else if (!sectionsId.equals(other.sectionsId)) {
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
		return true;
	}

	//toString
	@Override
	public String toString() {
		return "UpdateScheduleDto [sectionsId=" + sectionsId + ", termsId=" + termsId + ", daysId=" + daysId
				+ ", startId=" + startId + ", endId=" + endId + ", facultyId=" + facultyId + "]";
	}
	
	
	/**
	 * Creates builder to build {@link RegisterUserDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	
	/**
	 * Builder to build {@link RegisterUserDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private Integer sectionsId;
		private Integer termsId;
		private Integer daysId;
		private Integer startId;
		private Integer endId;
		private Integer facultyId;

		private Builder()
		{
		}

		public Builder withSectionsId(Integer sectionsId)
		{
			this.sectionsId = sectionsId;
			return this;
		}

		public Builder withTermsId(Integer termsId)
		{
			this.termsId = termsId;
			return this;
		}

		public Builder withDaysId(Integer daysId)
		{
			this.daysId = daysId;
			return this;
		}

		public Builder withStartId(Integer startId)
		{
			this.startId = startId;
			return this;
		}

		public Builder withEndId(Integer endId)
		{
			this.endId = endId;
			return this;
		}

		public Builder withFacultyId(Integer facultyId)
		{
			this.facultyId = facultyId;
			return this;
		}
		
		public UpdateScheduleDto build()
		{
			return new UpdateScheduleDto(this);
		}
	}
	
	
}
