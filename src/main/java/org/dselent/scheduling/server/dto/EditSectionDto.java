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
public class EditSectionDto
{
	private final String name;
	private final Integer id;
	private final Integer termID;
	private final Integer sectionTypeID;
	private final Integer daysID;
	private final Integer coursesID;
	private final Integer startID;
	private final Integer endID;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private EditSectionDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		
		this.name = builder.name;
		this.id = builder.id;
		this.termID = builder.termID;
		this.sectionTypeID = builder.sectionTypeID;
		this.daysID = builder.daysID;
		this.coursesID = builder.coursesID;
		this.startID = builder.startID;
		this.endID = builder.endID;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.name == null)
		{
			throw new IllegalStateException("name cannot be null");
		}
		else if(this.id == null)
		{
			throw new IllegalStateException("id cannot be null");
		}
		else if(this.termID == null)
		{
			throw new IllegalStateException("termID cannot be null");
		}
		else if(this.sectionTypeID == null)
		{
			throw new IllegalStateException("sectionTypeID cannot be null");
		}
		else if(this.daysID == null)
		{
			throw new IllegalStateException("daysID cannot be null");
		}
		else if(this.coursesID == null)
		{
			throw new IllegalStateException("coursesID cannot be null");
		}
		else if(this.startID == null)
		{
			throw new IllegalStateException("startID cannot be null");
		}
		else if(this.endID == null)
		{
			throw new IllegalStateException("endID cannot be null");
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getId()
	{
		return id;
	}

	public Integer getTermID()
	{
		return termID;
	}

	public Integer getSectionTypeID() {
		return sectionTypeID;
	}

	public Integer getDaysID() {
		return daysID;
	}

	public Integer getCoursesID() {
		return coursesID;
	}

	public Integer getStartID() {
		return startID;
	}

	public Integer getEndID() {
		return endID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursesID == null) ? 0 : coursesID.hashCode());
		result = prime * result + ((daysID == null) ? 0 : daysID.hashCode());
		result = prime * result + ((endID == null) ? 0 : endID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sectionTypeID == null) ? 0 : sectionTypeID.hashCode());
		result = prime * result + ((startID == null) ? 0 : startID.hashCode());
		result = prime * result + ((termID == null) ? 0 : termID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EditSectionDto other = (EditSectionDto) obj;
		if (coursesID == null) {
			if (other.coursesID != null)
				return false;
		} else if (!coursesID.equals(other.coursesID))
			return false;
		if (daysID == null) {
			if (other.daysID != null)
				return false;
		} else if (!daysID.equals(other.daysID))
			return false;
		if (endID == null) {
			if (other.endID != null)
				return false;
		} else if (!endID.equals(other.endID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sectionTypeID == null) {
			if (other.sectionTypeID != null)
				return false;
		} else if (!sectionTypeID.equals(other.sectionTypeID))
			return false;
		if (startID == null) {
			if (other.startID != null)
				return false;
		} else if (!startID.equals(other.startID))
			return false;
		if (termID == null) {
			if (other.termID != null)
				return false;
		} else if (!termID.equals(other.termID))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("AddSectionDto [name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", termID=");
		builder.append(termID);
		builder.append(", sectionTypeID=");
		builder.append(sectionTypeID);
		builder.append(", daysID=");
		builder.append(daysID);
		builder.append(", coursesID=");
		builder.append(coursesID);
		builder.append(", startID=");
		builder.append(startID);
		builder.append(", endID=");
		builder.append(endID);
		builder.append("]");
		return builder.toString();
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
		private String name;
		private Integer id;
		private Integer termID;
		private Integer sectionTypeID;
		private Integer daysID;
		private Integer coursesID;
		private Integer startID;
		private Integer endID;

		private Builder()
		{
		}

		public Builder withName(String name)
		{
			this.name = name;
			return this;
		}
		
		public Builder withId(Integer id)
		{
			this.id = id;
			return this;
		}

		public Builder withTermID(Integer termID)
		{
			this.termID = termID;
			return this;
		}

		public Builder withSectionTypeID(Integer sectionTypeID)
		{
			this.sectionTypeID = sectionTypeID;
			return this;
		}

		public Builder withDaysID(Integer daysID)
		{
			this.daysID = daysID;
			return this;
		}
		
		public Builder withCoursesID(Integer coursesID)
		{
			this.coursesID = coursesID;
			return this;
		}
		
		public Builder withStartID(Integer startID)
		{
			this.startID = startID;
			return this;
		}
		
		public Builder withEndID(Integer endID)
		{
			this.endID = endID;
			return this;
		}

		public EditSectionDto build()
		{
			return new EditSectionDto(this);
		}
	}
}
