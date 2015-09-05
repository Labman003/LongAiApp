package com.ouzhouren.longai.model;

public class MessageVO {
		private String message;
		private java.sql.Timestamp time;
		private int to;
		private int from;
		public int getFrom() {
			return from;
		}
		public void setFrom(int from) {
			this.from = from;
		}
		public int getTo() {
			return to;
		}
		public void setTo(int to) {
			this.to = to;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public java.sql.Timestamp getTime() {
			return time;
		}
		public void setTime(java.sql.Timestamp time) {
			this.time = time;
		}
}
