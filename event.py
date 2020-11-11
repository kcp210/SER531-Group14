class Event:
    def __init__(self, name, start_time, end_time, persons):
        self.name = name
        self.start_time = start_time
        self.end_time = end_time
        self.persons = persons

    def to_event_csv_row(self):
        ret_val = [self.name, self.start_time, self.end_time, '|'.join(self.persons)]

        return ret_val
