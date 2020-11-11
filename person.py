class Person:
    def __init__(self, first_name, last_name, phone_number, gender, dob):
        self.first_name = first_name
        self.last_name = last_name
        self.phone_number = phone_number
        self.gender = gender
        self.dob = dob
        self.medical_conditions = []

    def add_medical_conditions(self, medical_condition):
        self.medical_conditions.append(medical_condition)

    def to_person_csv_row(self):
        ret_val = [self.first_name, self.last_name, self.phone_number, self.gender, self.dob]
        return ret_val

    def to_person_details_csv_row(self):
        ret_val = [self.first_name, self.last_name, self.dob, '|'.join(self.medical_conditions)]
        return ret_val
