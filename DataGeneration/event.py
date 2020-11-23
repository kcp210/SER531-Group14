class Event:
    def __init__(self, event_id, name, start_time, end_time, persons):
        self.event_id = event_id
        self.name = name
        self.start_time = start_time
        self.end_time = end_time
        self.persons = persons

    def to_event_csv_row(self, person):
        ret_val = [self.event_id, self.name, self.start_time, self.end_time, person]

        return ret_val
