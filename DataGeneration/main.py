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
    file_name_for_given_users = '../data/users.csv'

    # Tables to Write To
    file_name_for_persons = '../data/Person.csv'
    file_name_for_events = '../data/Event.csv'
    file_name_for_person_details = '../data/PersonDetails.csv'

    # Headers
    event_headers = ['ID', 'Name', 'Start Time', 'End Time', 'Attendee']
    person_headers = ['ID', 'Fname', 'Lname', 'Phone', 'Gender', 'DOB']
    person_details_headers = ['Person ID', 'Fname', 'Lname', 'DOB','Medical Condition ID', 'Medical Conditions']

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
                person_id = "Person" + str(row[0])
                first_name = row[1]
                last_name = row[2]
                gender = row[3]
                dob = row[4]
                phone_number = row[5]

                new_person = Person(person_id, first_name, last_name, phone_number, gender, dob)

                person_array.append(new_person)

    # Tag Persons with Medical Conditions
    for person in person_array:
        id = random.randint(0, len(medical_conditions) - 1)
        medical_condition = medical_conditions[id]
        person.add_medical_conditions(id, medical_condition)

    # Create Events
    event_array = []
    id_counter = 0
    for i in range(len(event_names)):
        event_id = id_counter
        id_counter += 1

        event_name = event_names[i]
        event_start_time = event_start_times[i]
        event_end_time = event_end_times[i]

        attendees = []
        for person in person_array:
            did_attend = random.randint(0, 1)
            if did_attend:
                attendees.append(person.person_id)

        new_event = Event("Event" + str(event_id), event_name, event_start_time, event_end_time, attendees)
        event_array.append(new_event)

    # Write Event CSV File
    with open(file_name_for_events, "w") as csv_file:
        writer = csv.writer(csv_file)
        writer.writerow(event_headers)
        for event in event_array:
            for person in event.persons:
                row = event.to_event_csv_row(person)
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
