# Data Script

import csv
import random


def row_of_array_to_csv_string(data):
    ret_val = ''
    for column in range(len(data)):
        ret_val += data[column]
        if column != len(data) - 1:
            ret_val += ','

    return ret_val


# Entrypoint
if __name__ == '__main__':

    # Valid Events / File Name to Read/Write To
    events = ['Concert', 'Orchestra', 'Church', 'Sports Game']
    file = 'archive/abridgedContacts.csv'

    # Read in Current Data
    data_array = []     # Used to Hold Data
    empty_cell = ''     # Depicts Empty Cell
    with open(file) as csvFile:
        reader = csv.reader(csvFile)
        for row in reader:
            if row[0] == empty_cell:
                break
            else:
                row.append('')
                data_array.append(row)

    # Add in Header + New Data
    new_column_index = len(data_array[0]) - 1
    data_array[0][new_column_index] = 'events'   # New Header
    for row in range(1, len(data_array)):
        random_event = events[random.randint(0, len(events) - 1)]
        data_array[row][new_column_index] = random_event

    # Write Back to CSV File
    with open(file, "w") as csv_file:
        writer = csv.writer(csv_file)
        for line in data_array:
            row = row_of_array_to_csv_string(line)
            writer.writerow(line)
