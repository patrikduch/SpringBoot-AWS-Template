package com.patrikduch.springboot_aws_api_core.enums;

/**
 * Enum for role determination.
 * @author Patrik Duch
 */
public enum RoleEnum {
    ADMIN {
        public String toString() {
            return "Admin";
        }
    },

    REVIEWER {
        public String toString() {
            return "Reviewer";
        }
    },
    SUBMITTER {
        public String toString() {
            return "Submitter";
        }
    }
}