# Data Script

import csv
import random

from event import Event
from person import Person


def array_to_csv_row(array):
    ret_val = ''
    for value in range(len(array)):
        ret_val += array[value]
        if value != len(array) - 1:
            ret_val += ','

    print(ret_val)
    return ret_val


# Entrypoint
if __name__ == '__main__':

    # Valid Events
    event_names = ['Concert', 'Orchestra', 'Church', 'Sports Game']
    event_start_times = ['8/1/20 10:38', '8/3/20 3:43', '8/3/20 1:43', '8/3/20 10:12']
    event_end_times = ['8/1/20 11:13', '8/3/20 4:28', '8/3/20 2:24', '8/3/20 10:20']

    # Valid Medical Conditions
    medical_conditions = ['None', 'Heart Disease', 'Diabetes']

    # File to Read From
    file_name_for_given_users = 'archive/abridgedUsers.csv'

    # Tables to Write To
    file_name_for_persons = 'archive/Person.csv'
    file_name_for_events = 'archive/Event.csv'
    file_name_for_person_details = 'archive/PersonDetails.csv'

    # Headers
    event_headers = ['Name', 'Start Time', 'End Time', 'Attendees']
    person_headers = ['Fname', 'Lname', 'Phone', 'Gender', 'DOB']
    person_details_headers = ['Fname', 'Lname', 'DOB', 'Medical Conditions']

    # Read in Persons
    person_array = []     # Used to Hold Data
    empty_cell = ''     # Depicts Empty Cell

    with open(file_name_for_given_users) as csvFile:
        reader = csv.reader(csvFile)
        next(reader)    # Skip Header Line
        for row in reader:
            if row[0] == empty_cell:
                break
            else:
                first_name = row[1]
                last_name = row[2]
                gender = row[3]
                dob = row[4]
                phone_number = row[5]

                new_person = Person(first_name, last_name, phone_number, gender, dob)

                person_array.append(new_person)

    # Tag Persons with Medical Conditions
    for person in person_array:
        medical_condition = medical_conditions[random.randint(0, len(medical_conditions) - 1)]
        person.add_medical_conditions(medical_condition)

    # Create Events
    event_array = []
    for i in range(len(event_names)):
        event_name = event_names[i]
        event_start_time = event_start_times[i]
        event_end_time = event_end_times[i]

        attendees = []
        for person in person_array:
            did_attend = random.randint(0, 1)
            if did_attend:
                attendees.append(person.first_name + ' ' + person.last_name)

        new_event = Event(event_name, event_start_time, event_end_time, attendees)
        event_array.append(new_event)

    # Write Event CSV File
    with open(file_name_for_events, "w") as csv_file:
        writer = csv.writer(csv_file)
        writer.writerow(event_headers)
        for event in event_array:
            row = event.to_event_csv_row()
            writer.writerow(row)

    # Write Person CSV File
    with open(file_name_for_persons, "w") as csv_file:
        writer = csv.writer(csv_file)
        writer.writerow(person_headers)
        for person in person_array:
            row = person.to_person_csv_row()
            writer.writerow(row)

    # Write Person Details CSV File
    with open(file_name_for_person_details, "w") as csv_file:
        writer = csv.writer(csv_file)
        writer.writerow(person_details_headers)
        for person in person_array:
            row = person.to_person_details_csv_row()
            writer.writerow(row)
