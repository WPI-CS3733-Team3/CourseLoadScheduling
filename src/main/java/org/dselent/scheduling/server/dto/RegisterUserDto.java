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
public class RegisterUserDto
{
	private final String userName;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String password;

	// I added to the auto-generated code
	@Generated("SparkTools")
	private RegisterUserDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used

		this.userName = builder.userName;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.password = builder.password;

		// making claim that none of these can be null
		// add other state checks here as necessary

		if(this.userName == null)
		{
			throw new IllegalStateException("userName cannot be null");
		}
		else if(this.firstName == null)
		{
			throw new IllegalStateException("firstName cannot be null");
		}
		else if(this.lastName == null)
		{
			throw new IllegalStateException("lastName cannot be null");
		}
		else if(this.email == null)
		{
			throw new IllegalStateException("email cannot be null");
		}
		else if(this.password == null)
		{
			throw new IllegalStateException("password cannot be null");
		}
	}

	public String getUserName()
	{
		return userName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof RegisterUserDto))
		{
			return false;
		}
		RegisterUserDto other = (RegisterUserDto) obj;
		if (email == null)
		{
			if (other.email != null)
			{
				return false;
			}
		}
		else if (!email.equals(other.email))
		{
			return false;
		}
		if (firstName == null)
		{
			if (other.firstName != null)
			{
				return false;
			}
		}
		else if (!firstName.equals(other.firstName))
		{
			return false;
		}
		if (lastName == null)
		{
			if (other.lastName != null)
			{
				return false;
			}
		}
		else if (!lastName.equals(other.lastName))
		{
			return false;
		}
		if (password == null)
		{
			if (other.password != null)
			{
				return false;
			}
		}
		else if (!password.equals(other.password))
		{
			return false;
		}
		if (userName == null)
		{
			if (other.userName != null)
			{
				return false;
			}
		}
		else if (!userName.equals(other.userName))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("RegisterUserDto [userName=");
		builder.append(userName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
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
		private String userName;
		private String firstName;
		private String lastName;
		private String email;
		private String password;

		private Builder()
		{
		}

		public Builder withUserName(String userName)
		{
			this.userName = userName;
			return this;
		}

		public Builder withFirstName(String firstName)
		{
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName)
		{
			this.lastName = lastName;
			return this;
		}

		public Builder withEmail(String email)
		{
			this.email = email;
			return this;
		}

		public Builder withPassword(String password)
		{
			this.password = password;
			return this;
		}

		public RegisterUserDto build()
		{
			return new RegisterUserDto(this);
		}
	}
}
